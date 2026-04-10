package Presentation;

import DataAcess.BillDAO;
import DataAcess.ClientDAO;
import DataAcess.OrderDAO;
import DataAcess.ProductDAO;
import Model.Bill;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Clasa PlaceOrder gestionează interfața grafică pentru plasarea unei comenzi.
 * Permite selectarea unui client și a unui produs, specificarea cantității,
 * vizualizarea comenzilor și facturilor existente.
 * Aceasta se ocupă de validarea datelor, actualizarea stocului de produse,
 * inserarea comenzii și generarea billului.
 */
public class PlaceOrder implements ActionListener {
    /**
     * Metoda apelată la inițierea plasării unei comenzi.
     * Creează o fereastră cu componente pentru selectarea clientului, produsului, cantității,
     * butoane pentru vizualizarea comenzilor, facturilor și plasarea efectivă a comenzii.
     * La plasarea comenzii se verifică dacă există stoc suficient,
     * se actualizează baza de date și se generează billul.
     * @param e evenimentul care declanșează acțiunea
     */
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Create Order");
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 15, 15));
        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        BillDAO billDAO = new BillDAO();

        try {
            List<Client> clients = clientDAO.findAll();
            List<Product> products = productDAO.findAll();

            JComboBox<Client> clientCombo = new JComboBox<>(clients.toArray(new Client[0]));
            JComboBox<Product> productCombo = new JComboBox<>(products.toArray(new Product[0]));
            JTextField quantityField = new JTextField();

            frame.add(new JLabel("Select Client:"));
            frame.add(clientCombo);
            frame.add(new JLabel("Select Product:"));
            frame.add(productCombo);
            frame.add(new JLabel("Quantity:"));
            frame.add(quantityField);
            JButton viewButton = new JButton("View Orders");
            JButton createButton = new JButton("Place Order");
            JButton viewBillButton = new JButton("View Bills");
            frame.add(new JLabel());
            frame.add(new JLabel());
            frame.add(viewButton);
            frame.add(viewBillButton);
            frame.add(new JLabel());
            frame.add(createButton);

            viewBillButton.addActionListener(ev -> {
                JFrame billView = new JFrame("View Bills");
                billView.setSize(800, 500);
                billView.setLocationRelativeTo(null);
                billView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                try {
                    List<Bill> bills = billDAO.findAll();
                    String[] headers = TableBuilder.getTableHeaders(Bill.class);
                    Object[][] data = TableBuilder.getTableData(bills);
                    JTable table = new JTable(data, headers);
                    billView.add(new JScrollPane(table));
                    billView.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(billView, "Error loading bills: " + ex.getMessage());
                }
            });

            viewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame ordersView = new JFrame("View Orders");
                    ordersView.setSize(800, 500);
                    ordersView.setLocationRelativeTo(null);
                    ordersView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    try {
                        OrderDAO orderDAO = new OrderDAO();
                        List<Orders> orders =orderDAO.findAll();

                        String[] headers = TableBuilder.getTableHeaders(Orders.class);
                        Object[][] data = TableBuilder.getTableData(orders);

                        JTable table = new JTable(data, headers);
                        JScrollPane scrollPane = new JScrollPane(table);

                        ordersView.add(scrollPane);
                        ordersView.setVisible(true);
                    } catch (IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ordersView, "Error loading orders: " + ex.getMessage());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }


                }
            });

            createButton.addActionListener(ev -> {
                try {
                    Client selectedClient = (Client) clientCombo.getSelectedItem();
                    Product selectedProduct = (Product) productCombo.getSelectedItem();
                    int quantity = Integer.parseInt(quantityField.getText());

                    if (selectedProduct.getQuantityInStock() < quantity) {
                        JOptionPane.showMessageDialog(frame, "Not enough stock available.");
                        return;
                    }
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(frame, "Introduceți o cantitate validă (> 0).");
                        return;
                    }

                    Orders order = new Orders();
                    order.setClientId(selectedClient.getId());
                    order.setClientName(selectedClient.getName());
                    order.setProductId(selectedProduct.getId());
                    order.setProductName(selectedProduct.getName());
                    order.setQuantity(quantity);
                    order.setOrderDate(LocalDateTime.now());
                    order.setTotalAmount(selectedProduct.getPrice() * quantity);

                    orderDAO.insert(order);
                    selectedProduct.setQuantityInStock(selectedProduct.getQuantityInStock() - quantity);
                    productDAO.update(selectedProduct);
                    Bill bill = new Bill(0,selectedClient.getName(), selectedProduct.getName(), quantity, order.getTotalAmount(), LocalDateTime.now());
                    billDAO.insert(bill);
                    JOptionPane.showMessageDialog(frame, "Order placed successfully.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            });

            frame.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
        }
    }
}
