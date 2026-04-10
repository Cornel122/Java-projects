package Model;

import java.sql.Date;

public class Stoc {
    private int farmId;
    private String farmNume;
    private int medId;
    private String denumire;
    private String producator;
    private String imagine;
    private int cantitate;
    private boolean disponibilitate;
    private Date valabilitate;

    public Stoc(int farmId, String farmNume, int medId, String denumire,
                String producator, String imagine, int cantitate,
                boolean disponibilitate, Date valabilitate) {
        this.farmId = farmId;
        this.farmNume = farmNume;
        this.medId = medId;
        this.denumire = denumire;
        this.producator = producator;
        this.imagine = imagine;
        this.cantitate = cantitate;
        this.disponibilitate = disponibilitate;
        this.valabilitate = valabilitate;
    }

    public int getFarmId() { return farmId; }
    public String getFarmNume() { return farmNume; }
    public int getMedId() { return medId; }
    public String getDenumire() { return denumire; }
    public String getProducator() { return producator; }
    public String getImagine() { return imagine; }
    public int getCantitate() { return cantitate; }
    public boolean isDisponibilitate() { return disponibilitate; }
    public Date getValabilitate() { return valabilitate; }
}