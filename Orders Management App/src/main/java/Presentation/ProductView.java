package Presentation;


import BusniessLogic.ProductBLL;
import DataAcess.ClientDAO;
import DataAcess.ProductDAO;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Clasa {@code ProductView} implementează {@code ActionListener} și afișează
 * o fereastră care conține o tabelă cu lista tuturor produselor din baza de date.
 *
 * <p>La declanșarea evenimentului, se interoghează baza de date prin {@code ProductDAO}
 * pentru a obține lista produselor, apoi datele sunt afișate într-un {@code JTable} cu
 * scroll.</p>
 * <p>Dacă apare o eroare la accesarea bazei de date sau la reflectarea câmpurilor,
 * se afișează un mesaj de eroare utilizatorului.</p>
 */
public class ProductView implements ActionListener {
    /**
     * Metoda apelată la apăsarea butonului de vizualizare produse.
     * Creează o fereastră care afișează toate produsele în tabel.
     * @param e evenimentul de acțiune declanșat
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame productView = new JFrame("View Products");
        productView.setSize(800, 500);
        productView.setLocationRelativeTo(null);
        productView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            ProductBLL productBLL = new ProductBLL();
            List<Product> products = productBLL.getAllProducts();

            String[] headers = TableBuilder.getTableHeaders(Product.class);
            Object[][] data = TableBuilder.getTableData(products);

            JTable table = new JTable(data, headers);
            JScrollPane scrollPane = new JScrollPane(table);

            productView.add(scrollPane);
            productView.setVisible(true);
        } catch (IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(productView, "Error loading products: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
