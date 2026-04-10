package DataAcess;

import Model.Bill;
import Connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clasa DAO pentru obiectul {@link Bill}, utilizată pentru a efectua operații
 * de acces la date în tabela Log din baza de date.
 *
 * Metode disponibile:
 * <ul>
 *     <li>{@code findAll()} – returnează toate înregistrările din tabelă</li>
 *     <li>{@code insert(Bill bill)} – inserează o nouă factură în tabelă</li>
 * </ul>
 *
 *
 * este folosita numai la insert si read.
 */
public class BillDAO {
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM Log";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("billId"),
                        rs.getString("clientName"),
                        rs.getString("productName"),
                        rs.getInt("quantity"),
                        rs.getFloat("totalPrice"),
                        rs.getTimestamp("data").toLocalDateTime()
                );
                bills.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public void insert(Bill bill) {
        String sql = "INSERT INTO Log (clientName, productName, quantity, totalPrice, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bill.clientName());
            stmt.setString(2, bill.productName());
            stmt.setInt(3, bill.quantity());
            stmt.setDouble(4, bill.totalPrice());
            stmt.setTimestamp(5, Timestamp.valueOf(bill.data()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
