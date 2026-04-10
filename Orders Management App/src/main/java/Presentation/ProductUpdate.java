package Presentation;

import BusniessLogic.ProductBLL;
import DataAcess.ProductDAO;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa ProductUpdate gestionează actualizarea unui produs existent în baza de date.
 * Utilizatorul este ghidat prin dialoguri JOptionPane pentru a introduce
 * id-ul produsului, noul nume, noul preț și noua cantitate.
 * Datele sunt validate și apoi produsul este actualizat în baza de date.
 * Mesajele de succes sau eroare sunt afișate utilizatorului.
 */
public class ProductUpdate implements ActionListener {

    /**
     * Metoda apelată la evenimentul de acțiune
     * Realizează pașii pentru actualizarea unui produs:
     * solicită id-ul produsului, noul nume, prețul și cantitatea,
     * validează toate datele, apoi actualizează produsul în baza de date.
     * Afișează mesaje corespunzătoare pentru succes sau erori.
     * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ProductBLL productBLL = new ProductBLL();

        String productId = JOptionPane.showInputDialog(null, "Enter Product ID to update:",
                "Update Product", JOptionPane.QUESTION_MESSAGE);
        if (productId == null || productId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Product ID entered!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
            id = Integer.parseInt(productId);

        String newName = JOptionPane.showInputDialog(null, "Enter new name for Product ID " + productId + ":",
                "Update Product", JOptionPane.QUESTION_MESSAGE);


        String priceStr = JOptionPane.showInputDialog(null, "Enter new price for Product ID " + productId + ":",
                "Update Product", JOptionPane.QUESTION_MESSAGE);
        float newPrice;

            newPrice = Float.parseFloat(priceStr);


        String quantityStr = JOptionPane.showInputDialog(null, "Enter new quantity in stock for Product ID " + productId + ":",
                "Update Product", JOptionPane.QUESTION_MESSAGE);
        int newQuantity;

            newQuantity = Integer.parseInt(quantityStr);

        try {
            productBLL.updateProduct(new Product(id, newName, newPrice, newQuantity));
            JOptionPane.showMessageDialog(null, "Product with ID " + productId + " has been updated successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error updating product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
