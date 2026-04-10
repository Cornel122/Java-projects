package Model.Repository;

import Model.Stoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StocRepository {

    public void addStoc(int farmId, int medId, int cantitate, boolean disponibil, Date valabilitate) {

        String sql = "INSERT INTO Stoc(id_farm, id_med, cantitate, disponibilitate, valabilitate) VALUES(?,?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, farmId);
            ps.setInt(2, medId);
            ps.setInt(3, cantitate);
            ps.setBoolean(4, disponibil);
            ps.setDate(5, valabilitate);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Stoc> getMedicamenteFarmacie(int farmId){

        List<Stoc> list = new ArrayList<>();

        String sql =
                "SELECT f.id as farmId, f.nume as farmNume, m.id as medId, m.denumire, m.producator, m.imagine, " +
                        "s.cantitate, s.disponibilitate, s.valabilitate " +
                        "FROM Stoc s " +
                        "JOIN Farmacie f ON f.id = s.id_farm " +
                        "JOIN Medicament m ON m.id = s.id_med " +
                        "WHERE s.id_farm = ? " +
                        "ORDER BY m.denumire";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, farmId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(new Stoc(
                        rs.getInt("farmId"),
                        rs.getString("farmNume"),
                        rs.getInt("medId"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine"),
                        rs.getInt("cantitate"),
                        rs.getBoolean("disponibilitate"),
                        rs.getDate("valabilitate")
                ));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Stoc> filterByDisponibilitate(int farmId, boolean disponibil) {

        List<Stoc> list = new ArrayList<>();

        String sql =
                "SELECT f.id as farmId, f.nume as farmNume, m.id as medId, m.denumire, m.producator, m.imagine, " +
                        "s.cantitate, s.disponibilitate, s.valabilitate " +
                        "FROM Stoc s " +
                        "JOIN Farmacie f ON f.id = s.id_farm " +
                        "JOIN Medicament m ON m.id = s.id_med " +
                        "WHERE s.id_farm = ? AND s.disponibilitate = ? " +
                        "ORDER BY m.denumire";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, farmId);
            ps.setBoolean(2, disponibil);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(new Stoc(
                        rs.getInt("farmId"),
                        rs.getString("farmNume"),
                        rs.getInt("medId"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine"),
                        rs.getInt("cantitate"),
                        rs.getBoolean("disponibilitate"),
                        rs.getDate("valabilitate")
                ));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Stoc> filterByProducator(int farmId, String producator) {

        List<Stoc> list = new ArrayList<>();

        String sql =
                "SELECT f.id as farmId, f.nume as farmNume, m.id as medId, m.denumire, m.producator, m.imagine, " +
                        "s.cantitate, s.disponibilitate, s.valabilitate " +
                        "FROM Stoc s " +
                        "JOIN Farmacie f ON f.id = s.id_farm " +
                        "JOIN Medicament m ON m.id = s.id_med " +
                        "WHERE s.id_farm = ? AND m.producator LIKE ? " +
                        "ORDER BY m.denumire";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, farmId);
            ps.setString(2, "%" + producator + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(new Stoc(
                        rs.getInt("farmId"),
                        rs.getString("farmNume"),
                        rs.getInt("medId"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine"),
                        rs.getInt("cantitate"),
                        rs.getBoolean("disponibilitate"),
                        rs.getDate("valabilitate")
                ));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Stoc> filterByValabilitate(int farmId, Date beforeDate) {

        List<Stoc> list = new ArrayList<>();

        String sql =
                "SELECT f.id as farmId, f.nume as farmNume, m.id as medId, m.denumire, m.producator, m.imagine, " +
                        "s.cantitate, s.disponibilitate, s.valabilitate " +
                        "FROM Stoc s " +
                        "JOIN Farmacie f ON f.id = s.id_farm " +
                        "JOIN Medicament m ON m.id = s.id_med " +
                        "WHERE s.id_farm = ? AND s.valabilitate <= ? " +
                        "ORDER BY s.valabilitate";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, farmId);
            ps.setDate(2, beforeDate);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(new Stoc(
                        rs.getInt("farmId"),
                        rs.getString("farmNume"),
                        rs.getInt("medId"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine"),
                        rs.getInt("cantitate"),
                        rs.getBoolean("disponibilitate"),
                        rs.getDate("valabilitate")
                ));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Stoc> searchByDenumire(int farmId, String denumire) {

        List<Stoc> list = new ArrayList<>();

        String sql =
                "SELECT f.id as farmId, f.nume as farmNume, m.id as medId, m.denumire, m.producator, m.imagine, " +
                        "s.cantitate, s.disponibilitate, s.valabilitate " +
                        "FROM Stoc s " +
                        "JOIN Farmacie f ON f.id = s.id_farm " +
                        "JOIN Medicament m ON m.id = s.id_med " +
                        "WHERE s.id_farm = ? AND m.denumire LIKE ? " +
                        "ORDER BY m.denumire";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, farmId);
            ps.setString(2, "%" + denumire + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new Stoc(
                        rs.getInt("farmId"),
                        rs.getString("farmNume"),
                        rs.getInt("medId"),
                        rs.getString("denumire"),
                        rs.getString("producator"),
                        rs.getString("imagine"),
                        rs.getInt("cantitate"),
                        rs.getBoolean("disponibilitate"),
                        rs.getDate("valabilitate")
                ));
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}