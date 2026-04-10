package Model;

public class Medicament {
    private int id;
    private String denumire;
    private String producator;
    private String imagine;

    public Medicament(int id, String denumire, String producator, String imagine) {
        this.id = id;
        this.denumire = denumire;
        this.producator = producator;
        this.imagine = imagine;
    }

    public int getId() { return id; }
    public String getDenumire() { return denumire; }
    public String getProducator() { return producator; }
    public String getImagine() { return imagine; }

    public void setDenumire(String denumire) { this.denumire = denumire; }
    public void setProducator(String producator) { this.producator = producator; }
    public void setImagine(String imagine) { this.imagine = imagine; }

    @Override
    public String toString() {
        return denumire + " (" + producator + ")";
    }
}