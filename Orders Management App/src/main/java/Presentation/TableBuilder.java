package Presentation;

import java.lang.reflect.Field;
import java.util.List;
/**
 * Clasa TableBuilder oferă metode  pentru construirea datelor
 * utilizate în componente JTable.
 * Metodele folosesc reflecția pentru a extrage automat numele câmpurilor și valorile obiectelor
 * pentru a popula capetele de tabel și datele în format bidimensional
 * pentru toate clasele folosinduse de generic
 */
public class TableBuilder {
    /**
     * Returnează un sir de String-uri care reprezintă numele câmpurilor clasei primite ca parametru.
     * Aceste nume vor fi folosite ca header pentru tabel.
     * @param clasa clasa a cărei câmpuri vor fi extrase ca header de tabel
     * @param <T> tipul clasei
     * @return sir de String-uri cu numele câmpurilor clasei
     */

    public static <T> String[] getTableHeaders(Class<T> clasa) {
        Field[] fields = clasa.getDeclaredFields();
        String[] headers = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            headers[i] = fields[i].getName();
        }
        return headers;
    }
    /**
 * Convertește o listă de obiecte într-un sir bidimensional de tip Object,
 * reprezentând datele pentru un JTable.
 * Se folosesc reflecția pentru a accesa valorile câmpurilor fiecărui obiect.
 * @param objects lista de obiecte ce vor fi transformate în date pentru tabel
 * @param <T> tipul obiectelor din listă
 * @return un sir bidimensional de obiecte conținând valorile câmpurilor
 * @throws IllegalAccessException dacă accesul la câmpuri este restricționat
 */

    public static <T> Object[][] getTableData(List<T> objects) throws IllegalAccessException {
        if (objects.isEmpty()) return new Object[0][0];

        int rowCount = objects.size();
        Field[] fields = objects.get(0).getClass().getDeclaredFields();
        int colCount = fields.length;

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            T obj = objects.get(i);
            for (int j = 0; j < colCount; j++) {
                fields[j].setAccessible(true);
                data[i][j] = fields[j].get(obj);
            }
        }

        return data;
    }
}
