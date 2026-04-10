package Model;
/**
 * Clasa pentru definirea unui client.
 */

public class Client {
    /**
     * id ul clientului.
     */
    private int id;
    /**
     * numele clientului.
     */
    private String name;


public Client() {}
    /**
     * constructor cu parametri.
     *
     * @param id   id ul clientului
     * @param name Numele clientului
     */
    public Client(int id, String name) {
        this.id = id;
        this.name = name;

    }
    /**
     * Returnează  id ul clientului.
     *
     * @return  id ul clientului
     */
    public int getId() { return id; }
    /**
     * Setează ID-ul clientului.
     *
     * @param id noul ID
     */
    public void setId(int id) { this.id = id; }
    /**
     * Returnează numele clientului.
     *
     * @return numele clientului
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    /**
     * Afisare date client
     *
     * @return un string
     */

    public String toString() {
        return id + " - " + name;
}
}