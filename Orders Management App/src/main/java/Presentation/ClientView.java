package Presentation;

import BusniessLogic.ClientBLL;
import DataAcess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
/**
 * Clasa {@code ClientView} implementează {@code ActionListener} și afișează o fereastră
 * cu o listă de clienți preluați din baza de date.
 *
 * <p>La declanșarea acțiunii, se creează o fereastră care conține un tabel cu toate datele
 * clienților, utilizând clasele {@code ClientDAO} pentru acces la date și
 * {@code TableBuilder} pentru construirea tabelului.</p>
 *
 * <p>În cazul unor erori la accesarea bazei de date sau la reflectarea datelor,
 * acestea sunt afișate utilizatorului printr-un mesaj.</p>
 */

public class ClientView implements ActionListener{
    /**
     * Metoda apelată la apăsarea butonului pentru vizualizarea clienților.
     * Creează și afișează o fereastră cu tabelul clienților.
     * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame clientView = new JFrame("View Clients");
        clientView.setSize(800, 500);
        clientView.setLocationRelativeTo(null);
        clientView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            ClientBLL clientBLL=new ClientBLL();
           List<Client> clients = clientBLL.getAllClients();

            String[] headers = TableBuilder.getTableHeaders(Client.class);
            Object[][] data = TableBuilder.getTableData(clients);

            JTable table = new JTable(data, headers);
            JScrollPane scrollPane = new JScrollPane(table);

            clientView.add(scrollPane);
            clientView.setVisible(true);
        } catch (IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(clientView, "Error loading clients: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
