package Presentation;

import BusniessLogic.ClientBLL;
import DataAcess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa {@code ClientDelete} implementează {@code ActionListener} și gestionează
 * ștergerea unui client din baza de date pe baza unui id introdus de utilizator.
 *
 * <p>La activarea evenimentului  clasa afișează o fereastră
 * de dialog pentru a introduce id-ul clientului care trebuie șters, apoi încearcă
 * să șteargă clientul din baza de date folosind {@link ClientDAO}.</p>
 *
 * <p>În cazul în care nu se introduce niciun ID, se afișează un mesaj de avertizare.
 * Dacă ștergerea este realizată cu succes, se confirmă utilizatorului că clientul a fost șters.</p>
 */
public class ClientDelete implements ActionListener {
    /**
     * Metoda apelată la declanșarea acțiunii. Solicită id-ul clientului de șters,
     * efectuează ștergerea în baza de date și informează utilizatorul despre rezultat.
     * @param e evenimentul care declanșează acțiunea
     */
    public void actionPerformed(ActionEvent e) {
        ClientBLL clientBLL=new ClientBLL();
            String clientId = JOptionPane.showInputDialog(null, "Enter Client ID to delete:",
                    "Delete Client", JOptionPane.QUESTION_MESSAGE);

            try {
                clientBLL.deleteClient(Integer.parseInt(clientId));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(null, "Client with ID " + clientId + " has been deleted" );

        }
}
