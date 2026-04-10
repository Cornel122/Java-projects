package Presentation;

import BusniessLogic.ClientBLL;
import DataAcess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa {@code ClientInsert} implementează {@code ActionListener} și gestionează
 * inserarea unui nou client în baza de date.
 *
 * <p>La activarea acțiunii, clasa solicită utilizatorului să introducă un id pentru noul client
 * și verifică dacă acesta nu există deja în baza de date.
 * Dacă id-ul este valid și nu există, se solicită numele clientului, apoi se inserează noul client.</p>
 * <p>În cazul în care utilizatorul nu introduce date sau acestea sunt invalide,
 * se afișează mesaje corespunzătoare.</p>
 */
public class ClientInsert implements ActionListener {
    /**
     * Metoda apelată la declanșarea acțiunii. Solicită id-ul și numele clientului,
     * verifică existența clientului și, dacă este valid, îl inserează în baza de date.
     * @param e evenimentul care declanșează acțiunea
     */
    @Override

    public void actionPerformed(ActionEvent e) {
        ClientDAO clientDAO = new ClientDAO();
        ClientBLL clientBLL=new ClientBLL();
        String clientId = JOptionPane.showInputDialog(null, "Enter Client ID to be inserted:",
                "Insert Client", JOptionPane.QUESTION_MESSAGE);

        try {
            if (clientDAO.findById(Integer.parseInt(clientId)) != null) {
                JOptionPane.showMessageDialog(null, "Client ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error checking client ID: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newName = JOptionPane.showInputDialog(null, "Enter  name for Client ID " + clientId + ":",
                "Insert Client", JOptionPane.QUESTION_MESSAGE);



        try {
            clientBLL.addClient(new Client(Integer.parseInt(clientId), newName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        JOptionPane.showMessageDialog(null, "Client with ID " + clientId + " has been inserted with the name: " + newName);
    }
}
