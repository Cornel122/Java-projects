package Presentation;

import DataAcess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
/**
 * Clasa {@code ClientOperation} implementează {@code ActionListener} și gestionează
 * afișarea unei ferestre cu opțiuni pentru operații asupra entității Client.
 *
 * <p>Fereastra creată conține butoane pentru vizualizare, inserare, actualizare și ștergere
 * a clienților. Fiecare buton este legat de un {@code ActionListener} specific pentru
 * fiecare operație.</p>
 *
 * <p>La declanșarea evenimentului {@code actionPerformed}, clasa creează și afișează o
 * nouă fereastră cu butoanele pentru aceste operații.</p>

 */
public class ClientOperation implements ActionListener {
    /**
     * Metoda apelată la apăsarea butonului care a declanșat evenimentul.
     * Creează și afișează o fereastră cu opțiuni pentru operațiile CRUD pe clienți.
     *
     * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame clientFrame = new JFrame("Client Operations");
        clientFrame.setSize(800, 500);
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton viewBtn = new JButton("View Clients");
        viewBtn.setMaximumSize(new Dimension(200, 50));
        viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBtn.addActionListener( new ClientView());

        JButton insertBtn = new JButton("Insert Client");
        insertBtn.setMaximumSize(new Dimension(200, 50));
        insertBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertBtn.addActionListener( new ClientInsert());

        JButton updateBtn = new JButton("Update Client");
        updateBtn.setMaximumSize(new Dimension(200, 50));
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateBtn.addActionListener( new ClientUpdate());

        JButton deleteBtn = new JButton("Delete Client");
        deleteBtn.setMaximumSize(new Dimension(200, 50));
        deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteBtn.addActionListener( new ClientDelete());
        panel.add(Box.createVerticalStrut(30));
        panel.add(viewBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(insertBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(updateBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(deleteBtn);
        panel.add(Box.createVerticalStrut(30));
        clientFrame.add(panel);
        clientFrame.setVisible(true);


    }
}
