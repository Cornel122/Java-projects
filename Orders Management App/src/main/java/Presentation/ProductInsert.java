package Presentation;

import BusniessLogic.ProductBLL;
import DataAcess.ProductDAO;
import Model.Product;

import javax.swing.*;
/**
 * Această clasă gestionează inserarea unui nou produs în baza de date
 * prin intermediul dialogurilor de input pentru utilizator.
 * Solicită utilizatorului să introducă detaliile produsului,
 * validează datele, verifică dacă id-ul există deja și inserează produsul nou.
 * Folosește dialoguri JOptionPane pentru input și output către utilizator.
 */
public class ProductInsert implements java.awt.event.ActionListener {

    /**
     * Metoda apelată când are loc un eveniment
     * Solicită utilizatorului să introducă id-ul produsului, numele,
     * prețul și cantitatea în stoc, validează datele,
     * verifică existența id-ului și inserează produsul în baza de date.
     * Afișează mesaje de succes sau eroare către utilizator.
     * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        ProductBLL productBLL = new ProductBLL();

        String productId = JOptionPane.showInputDialog(null, "Enter Product ID to be inserted:",
                "Insert Product", JOptionPane.QUESTION_MESSAGE);


        int id;
            id = Integer.parseInt(productId);



        String name = JOptionPane.showInputDialog(null, "Enter name for Product ID " + productId + ":",
                "Insert Product", JOptionPane.QUESTION_MESSAGE);

        String priceStr = JOptionPane.showInputDialog(null, "Enter price for Product ID " + productId + ":",
                "Insert Product", JOptionPane.QUESTION_MESSAGE);
        float price;

            price = Float.parseFloat(priceStr);


        String quantityStr = JOptionPane.showInputDialog(null, "Enter quantity in stock for Product ID " + productId + ":",
                "Insert Product", JOptionPane.QUESTION_MESSAGE);
        int quantity;
        quantity = Integer.parseInt(priceStr);

        try {
             productBLL.addProduct(new Product(id, name, price, quantity));
            JOptionPane.showMessageDialog(null, "Product with ID " + productId + " has been inserted successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error inserting product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
