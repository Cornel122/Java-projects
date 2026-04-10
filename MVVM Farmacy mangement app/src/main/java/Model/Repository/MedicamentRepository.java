package Model.Repository;

import Model.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentRepository {

    public void add(Medicament m) {
        String sql = "INSERT INTO Medicament(denumire, producator, imagine) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getDenumire());
            ps.setString(2, m.getProducator());
            ps.setString(3, m.getImagine());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Medicament m) {
        String sql = "UPDATE Medicament SET denumire=?, producator=?, imagine=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getDenumire());
            ps.setString(2, m.getProducator());
            ps.setString(3, m.getImagine());
            ps.setInt(4, m.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Medicament WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Medicament> getAllSorted() {
        List<Medicament> list = new ArrayList<>();
        String sql = "SELECT * FROM Medicament ORDER BY denumire";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Medicament(
                        rs.getInt("id"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Medicament> searchByName(String name) {
        List<Medicament> list = new ArrayList<>();
        String sql = "SELECT * FROM Medicament WHERE denumire LIKE ? ORDER BY denumire";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Medicament(
                        rs.getInt("id"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}