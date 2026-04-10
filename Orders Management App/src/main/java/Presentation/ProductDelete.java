package Presentation;

import BusniessLogic.ProductBLL;
import DataAcess.ProductDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa ProductDelete gestionează ștergerea unui produs din baza de date.
 * Utilizatorul este solicitat să introducă id-ul produsului care urmează să fie șters,
 *iar, produsul este șters din baza de date.
 * Se afișează mesaje de succes sau eroare în funcție de rezultat.
 */
public class ProductDelete implements ActionListener {
    /**
     * Metoda apelată la acțiunea de ștergere a unui produs.
     * Solicită id-ul produsului de șters, validează datele și încearcă să șteargă produsul.
     * Afișează mesaje corespunzătoare pentru succes sau erori.
     * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ProductBLL productBLL = new ProductBLL();
        String productId = JOptionPane.showInputDialog(null, "Enter Product ID to delete:",
                "Delete Product", JOptionPane.QUESTION_MESSAGE);


        try {
            int id = Integer.parseInt(productId);
            productBLL.deleteProduct(id);
            JOptionPane.showMessageDialog(null, "Product with ID " + productId + " has been deleted.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Product ID! Must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
