package UserInterface.ui;

import se.systementor.DatabaseConnect.Database;
import se.systementor.model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * CashRegisterGUI is a graphical user interface for managing a simple cash register system.
 * It allows users to add items to a cart, display the cart's contents, and generate a dynamic receipt.
 */
public class CashRegisterGUI {
    private JPanel panel1;
    private JPanel panelRight;
    private JPanel panelLeft;
    private JTextArea receiptArea;
    private JPanel buttonsPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JButton payButton;

    private Database database = new Database();
    private List<Item> cartItems = new ArrayList<>(); // List to hold items added to the cart

    /**
     * Constructor to initialize the CashRegisterGUI. It dynamically creates buttons for each active product
     * from the database and sets up actions for adding items to the cart and generating receipts.
     */
    public CashRegisterGUI() {
        // Dynamically populate the buttons panel with active product buttons from the database
        for (Item item : database.activeProducts()) {
            JButton button = new JButton(item.getName() + " - " + item.getPrice());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add the selected item to the cart and update the receipt area
                    cartItems.add(item);
                    receiptArea.append(item.getName() + " added to the cart\n");
                }
            });
            buttonsPanel.add(button);
        }

        // Action listener for the "Add Receipt" button to generate a static receipt (for example purposes)
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiptArea.append("                     STEFANS SUPERSHOP\n");
                receiptArea.append("----------------------------------------------------\n");
                receiptArea.append("\n");
                receiptArea.append("Kvittonummer: 122        Datum: 2024-09-01 13:00:21\n");
                receiptArea.append("----------------------------------------------------\n");
                receiptArea.append("Kaffe Gevalia           5 *     51.00    =   255.00  \n");
                receiptArea.append("Nallebjörn              1 *     110.00   =   110.00  \n");
                receiptArea.append("Total                                        ------\n");
                receiptArea.append("                                             306.00\n");
                receiptArea.append("TACK FÖR DITT KÖP\n");
            }
        });

        // Action listener for the "Pay" button to finalize the transaction and display the receipt
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReceipt();  // Call the method to generate the receipt
            }
        });
    }

    /**
     * This method generates a dynamic receipt based on the items in the cart.
     * It displays the product name, quantity, price, total for each item,
     * and the overall total of all items in the cart.
     */
    private void generateReceipt() {
        double total = 0;

        // Clear the receipt area before generating a new one
        receiptArea.setText("");

        // Header for the receipt
        receiptArea.append("                     STEFANS SUPERSHOP\n");
        receiptArea.append("----------------------------------------------------\n");
        receiptArea.append("\n");

        // Iterate over the cart items and generate a line for each in the receipt
        for (Item item : cartItems) {
            double itemTotal = item.getPrice().doubleValue(); // Assuming 1 quantity for simplicity
            total += itemTotal;
            receiptArea.append(String.format("%-20s %4s * %-8.2f = %-8.2f\n",
                    item.getName(), 1, item.getPrice(), itemTotal));
        }

        // Display the total amount on the receipt
        receiptArea.append("----------------------------------------------------\n");
        receiptArea.append(String.format("Total: %-8.2f\n", total));
        receiptArea.append("TACK FÖR DITT KÖP\n");

        // Clear the cart after payment is completed
        cartItems.clear();
    }

    /**
     * This method runs the GUI application by creating a JFrame and displaying the panel1 content.
     * It is invoked from the Event Dispatch Thread to ensure thread-safety for UI components.
     */
    public void run() {
        // Run the UI inside the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cash Register");
            frame.setContentPane(panel1); // Set the main panel content
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);  // Center the frame on the screen
            frame.setSize(1000, 800); // Set fixed size for the window
            frame.setVisible(true); // Make the window visible
        });
    }

    /**
     * Method for custom component creation, if needed in the future.
     * Currently unused but can be customized for further UI components.
     */
    private void createUIComponents() {
        // Custom component creation logic if needed
    }
}
