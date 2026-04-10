package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa {@code ProductOperation} implementează {@code ActionListener} și reprezintă
 * interfața grafică pentru operațiuni legate de produse.
 *
 * <p>La declanșarea evenimentului, deschide o fereastră care oferă patru butoane:
 * vizualizarea, inserarea, actualizarea și ștergerea produselor.</p>
 *
 * <p>Fiecare buton are atașat un {@code ActionListener} corespunzător care gestionează
 * operația specifică asupra entității {@code Product}.</p>
 */
public class ProductOperation implements ActionListener {
    /**
     * Metoda apelată la apăsarea butonului pentru operațiuni cu produse.
     * Creează și afișează o fereastră cu butoane pentru diferite operațiuni CRUD.
     * @param e evenimentul care declanșează acțiunea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame clientFrame = new JFrame("Product Operations");
        clientFrame.setSize(800, 500);
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton viewBtn = new JButton("View Products");
        viewBtn.setMaximumSize(new Dimension(200, 50));
        viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBtn.addActionListener( new ProductView());

        JButton insertBtn = new JButton("Insert Product");
        insertBtn.setMaximumSize(new Dimension(200, 50));
        insertBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertBtn.addActionListener( new ProductInsert());

        JButton updateBtn = new JButton("Update Product");
        updateBtn.setMaximumSize(new Dimension(200, 50));
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateBtn.addActionListener( new ProductUpdate());

        JButton deleteBtn = new JButton("Delete Product");
        deleteBtn.setMaximumSize(new Dimension(200, 50));
        deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteBtn.addActionListener( new ProductDelete());
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
