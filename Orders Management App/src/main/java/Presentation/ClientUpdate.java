package Presentation;

import BusniessLogic.ClientBLL;
import DataAcess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa {@code ClientUpdate} implementează {@code ActionListener} și permite actualizarea
 * datelor unui client existent în baza de date.
 *
 * <p>La declanșarea acțiunii, se solicită utilizatorului să introducă id-ul clientului
 * care trebuie actualizat și noul nume al clientului. Dacă id-ul sau numele nu sunt
 * introduse corect, sunt afișate mesaje de eroare.</p>
 *
 * <p>Dacă datele sunt valide, clientul este actualizat în baza de date prin apelul metodei
 * {@code update} din {@code ClientDAO}.</p>
 *
 * <p>În caz de eroare la actualizare, excepția este propagată ca {@code RuntimeException}.</p>
 */
public class ClientUpdate implements ActionListener {
      /** Metoda apelată la apăsarea butonului de actualizare client.
       * Solicită id-ul clientului și noul nume, apoi actualizează clientul în baza de date.
       * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ClientBLL clientBLL=new ClientBLL();
        String clientId = JOptionPane.showInputDialog(null, "Enter Client ID to update:",
                "Update Client", JOptionPane.QUESTION_MESSAGE);

        String newName = JOptionPane.showInputDialog(null, "Enter new name for Client ID " + clientId + ":",
                "Update Client", JOptionPane.QUESTION_MESSAGE);

        try {
            clientBLL.updateClient(new Client(Integer.parseInt(clientId), newName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        JOptionPane.showMessageDialog(null, "Client with ID " + clientId + " updated to name: " + newName);
    }

    }

