package UserInterface.ui;

import se.systementor.DatabaseConnect.Database;
import se.systementor.Services.OrderDAO;
import se.systementor.Services.ProductDAO;
import se.systementor.model.Item;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CashRegisterGUI {
    private JFrame frame;
    private JPanel categoryPanel;
    private JPanel itemPanel;
    private JTextArea receiptArea;
    private JButton checkoutButton;
    private JButton statisticsButton;
    private JTextField quantityField; // Replaces textField1
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal totalVat = BigDecimal.ZERO;
    private final Database database = new Database();
    private final List<Item> cartItems = new ArrayList<>();
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;

    public CashRegisterGUI() {
        this.productDAO = new ProductDAO(database);
        this.orderDAO = new OrderDAO(database);

        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Cash Register");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        // Category panel for product buttons
        categoryPanel = new JPanel(new GridLayout(10, 1));
        frame.add(categoryPanel, BorderLayout.WEST);

        // Item panel for quantity input
        itemPanel = new JPanel(new GridLayout(0, 1));
        quantityField = new JTextField(10); // Initialize the quantity field
        itemPanel.add(new JLabel("Quantity:")); // Add a label for the quantity field
        itemPanel.add(quantityField); // Add the quantity field to the panel
        frame.add(itemPanel, BorderLayout.CENTER); // Add the item panel to the frame

        // Receipt area
        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setBackground(Color.WHITE);
        JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
        receiptScrollPane.getViewport().setPreferredSize(new Dimension(400, 400));
        frame.add(receiptScrollPane, BorderLayout.EAST);

        // Checkout button
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());
        frame.add(checkoutButton, BorderLayout.SOUTH);

        // Statistics button
        statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(e -> showStatistics());
        frame.add(statisticsButton, BorderLayout.NORTH);

        // Add product buttons
        addProductButtons();
    }

    private void addProductButtons() {
        try {
            List<Item> products = productDAO.getAllProducts();
            for (Item product : products) {
                JButton productButton = new JButton(product.getName() + " - " + product.getPrice());
                productButton.addActionListener(e -> addProductToCart(product));
                categoryPanel.add(productButton);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProductToCart(Item product) {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            cartItems.add(new Item(product.getId(), product.getName(), product.getPrice(), product.getVatRate(), product.getCategory(), quantity, product.getBarcode()));
            totalAmount = totalAmount.add(itemTotal);
            totalVat = totalVat.add(itemTotal.multiply(product.getVatRate()));
            generateReceipt();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateReceipt() {
        receiptArea.setText("");
        receiptArea.append(" STEFANS SUPERSHOP\n");
        receiptArea.append("---\n");

        for (Item item : cartItems) {
            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getStockQuantity()));
            receiptArea.append(String.format("%-20s %4d * %-8.2f = %-8.2f\n",
                    item.getName(), item.getStockQuantity(), item.getPrice(), itemTotal));
        }

        receiptArea.append("---\n");
        receiptArea.append(String.format("Subtotal: %-8.2f\n", totalAmount));
        receiptArea.append(String.format("VAT: %-8.2f\n", totalVat));
        receiptArea.append(String.format("Total: %-8.2f\n", totalAmount.add(totalVat)));
        receiptArea.append("TACK FÖR DITT KÖP\n");
    }

    private void checkout() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Cart is empty. Add items before checkout.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int orderId = orderDAO.createOrder(totalAmount.doubleValue(), totalVat.doubleValue());
        if (orderId != -1) {
            JOptionPane.showMessageDialog(frame, "Order created successfully with ID: " + orderId, "Success", JOptionPane.INFORMATION_MESSAGE);
            cartItems.clear();
            totalAmount = BigDecimal.ZERO;
            totalVat = BigDecimal.ZERO;
            generateReceipt();
        } else {
            JOptionPane.showMessageDialog(frame, "Error creating order", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showStatistics() {
        // Placeholder for statistics functionality
        JOptionPane.showMessageDialog(frame, "Statistics feature not implemented yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void run() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CashRegisterGUI().run();
    }
}