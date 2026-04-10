package DataAcess;

import Connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clasă generică DAO (Data Access Object) care oferă operații CRUD Create, Read, Update, Delete
 * pentru orice tip de entitate.
 *
 * @param <T> tipul entității gestionate de DAO
 */
public class GenericDAO<T> {
    /**
     * Clasa de tip T, utilizată pentru reflecție.
     */
    private final Class<T> type;
    /**
     * Constructor care determină tipul generic T folosind reflecția.
     */
    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * Generează o interogare SQL pentru a returna toate înregistrările din tabel.
     *
     * @return interogarea SQL "SELECT * FROM ..."
     */
    public String generateFindAll() {
        return "SELECT * FROM " + type.getSimpleName();
    }
    /**
     * Returnează o listă cu toate obiectele de tip T din baza de date.
     *
     * @return listă de obiecte T
     * @throws Exception în caz de eroare de conexiune sau reflecție
     */
    public List<T> findAll() throws Exception {
        String sql = generateFindAll();
        List<T> list = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                T obj = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(field.getName()));
                }
                list.add(obj);
            }
        }

        return list;
    }
    /**
     * Generează o interogare SQL pentru a căuta o înregistrare după id.
     *
     * @return interogarea SQL cu WHERE id = ?
     */
    public String generateFindById() {
        return "SELECT * FROM " + type.getSimpleName() + " WHERE id = ?";
    }
    /**
     * Caută un obiect de tip T în baza de date după id.
     *
     * @param id valoarea id
     * @return obiectul găsit sau null dacă nu există
     * @throws Exception în caz de eroare
     */
    public T findById(Object id) throws Exception {
        String sql = generateFindById();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                T obj = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(field.getName()));
                }
                return obj;
            }
        }

        return null;
    }
    /**
     * Generează o interogare SQL pentru inserarea unui obiect.
     *
     * @param obj obiectul de inserat
     * @return interogarea SQL pentru INSERT
     * @throws IllegalAccessException în caz de eroare la accesarea câmpurilor
     */

    public String generateInsert(T obj) throws IllegalAccessException {
        StringBuilder sql = new StringBuilder("INSERT INTO " + type.getSimpleName() + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(obj) != null) {
                sql.append(field.getName()).append(", ");
                values.append("?, ");
            }
        }

        sql.setLength(sql.length() - 2);
        values.setLength(values.length() - 2);
        sql.append(") ").append(values).append(")");

        return sql.toString();
    }
    /**
     * Inserează un obiect de tip T în baza de date.
     *
     * @param obj obiectul de inserat
     * @throws Exception în caz de eroare
     */
    public void insert(T obj) throws Exception {
        String sql = generateInsert(obj);
        List<Object> params = new ArrayList<>();

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(obj) != null) {
                params.add(field.get(obj));
            }
        }

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            stmt.executeUpdate();
        }
    }
    /**
     * Generează o interogare SQL pentru actualizarea unei înregistrări.
     *
     * @param obj obiectul de actualizat
     * @return interogarea SQL pentru UPDATE
     * @throws IllegalAccessException dacă apare o eroare la accesarea câmpurilor
     */
    public String generateUpdate(T obj) throws IllegalAccessException {
        StringBuilder sql = new StringBuilder("UPDATE " + type.getSimpleName() + " SET ");
        Object idValue = null;

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if ("id".equals(field.getName())) {
                idValue = value;
            } else if (value != null) {
                sql.append(field.getName()).append(" = ?, ");
            }
        }

        if (idValue == null) {
            throw new IllegalArgumentException();
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");

        return sql.toString();
    }
    /**
     * Actualizează un obiect existent în baza de date.
     *
     * @param obj obiectul de actualizat
     * @throws Exception în caz de eroare
     */

    public void update(T obj) throws Exception {
        String sql = generateUpdate(obj);
        List<Object> params = new ArrayList<>();
        Object idValue = null;

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if ("id".equals(field.getName())) {
                idValue = value;
            } else if (value != null) {
                params.add(value);
            }
        }

        params.add(idValue);

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            stmt.executeUpdate();
        }
    }
    /**
     * Generează o interogare SQL pentru ștergerea unei înregistrări după id.
     *
     * @return interogarea SQL pentru DELETE
     */
    public String generateDelete() {
        return "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
    }

    /**
     * Șterge o înregistrare din baza de date după ID.
     *
     * @param id idului înregistrării de șters
     * @throws Exception în caz de eroare
     */
    public void delete(Object id) throws Exception {
        String sql = generateDelete();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}
