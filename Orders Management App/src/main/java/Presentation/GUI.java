package Presentation;

import DataAcess.ClientDAO;
import DataAcess.GenericDAO;
import DataAcess.OrderDAO;
import DataAcess.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Clasa {@code GUI} reprezintă fereastra principală a aplicației de gestiune a comenzilor.
 * Aceasta oferă o interfață grafică pentru navigarea către funcționalități precum:
 * <ul>
 *     <li>Operații pentru clienți</li>
 *     <li>Operații pentru produse</li>
 *     <li>Crearea comenzilor</li>
 * </ul>
 *
 * Interfața este construită folosind biblioteca Swing și conține trei butoane mari,
 * fiecare cu un {@code ActionListener} dedicat pentru a deschide funcționalitatea corespunzătoare.
 */
public class GUI extends JFrame {


    /**
     * Constructorul clasei {@code GUI} inițializează fereastra principală și
     * adaugă butoanele pentru navigarea în aplicație.
     */
    public GUI() {
        setTitle("Orders Management");
        setSize(900, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 60));

        JButton clientButton = new JButton("Client Operations");
        JButton productButton = new JButton("Product Operations");
        JButton orderButton = new JButton("Create Order");

        Font btnFont = new Font("Arial", Font.PLAIN, 20);
        clientButton.setFont(btnFont);
        productButton.setFont(btnFont);
        orderButton.setFont(btnFont);
        orderButton.addActionListener(new PlaceOrder());
        productButton.addActionListener(new ProductOperation());

        Dimension btnSize = new Dimension(220, 70);
        clientButton.setPreferredSize(btnSize);
        productButton.setPreferredSize(btnSize);
        orderButton.setPreferredSize(btnSize);
        clientButton.addActionListener(new ClientOperation());

        mainPanel.add(clientButton);
        mainPanel.add(productButton);
        mainPanel.add(orderButton);

        add(mainPanel);
        setVisible(true);
    }
    /**
     * Punctul de intrare al aplicației.
     * Creează și afișează fereastra principală {@code GUI}.
     */

    public static void main(String[] args) {
        GUI gui = new GUI();


    }
}
