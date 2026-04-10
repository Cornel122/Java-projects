package Model.Repository;

import Model.Farmacie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmacieRepository {
    public void add(Farmacie f) {
        String sql = "INSERT INTO Farmacie(nume, adresa) VALUES(?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNume());
            ps.setString(2, f.getAdresa());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Farmacie f) {
        String sql = "UPDATE Farmacie SET nume=?, adresa=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNume());
            ps.setString(2, f.getAdresa());
            ps.setInt(3, f.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Farmacie WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Farmacie> getAll() {
        List<Farmacie> list = new ArrayList<>();
        String sql = "SELECT * FROM Farmacie ORDER BY nume";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Farmacie(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("adresa")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}