package se.systementor.Services;

import se.systementor.DatabaseConnect.Database;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * StatiskExport is a service class responsible for exporting sales statistics
 * from the database to an XML file. It extracts the total sales for the current
 * day and saves them to a file named `statistik.xml`.
 */
public class StatiskExport {

    /**
     * Exports today's sales statistics from the database to an XML file.
     *
     * This method connects to the database, retrieves today's orders from the
     * `ordrar` table, and writes the total sales (including VAT) for each order
     * to an XML file. The file is named `statistik.xml`.
     *
     * @throws Exception If an error occurs while accessing the database or writing to the file.
     */
    public static void exporteraTillXML() {
        Database database = new Database(); // Create an instance of the Database class
        try (Connection conn = database.getConnection()) {  // Call the non-static getConnection() method
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ordrar WHERE DATE(datum) = CURDATE()");

            FileWriter writer = new FileWriter("statistik.xml");
            writer.write("<SaleStatistics>\n");
            while (rs.next()) {
                writer.write("<TotalSalesInclVat>" + rs.getDouble("totalpris") + "</TotalSalesInclVat>\n");
            }
            writer.write("</SaleStatistics>");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error exporting data.");
        }
    }
}
