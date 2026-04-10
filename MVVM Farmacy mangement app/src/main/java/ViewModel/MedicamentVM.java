package ViewModel;

import Model.Medicament;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedicamentVM {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty denumire = new SimpleStringProperty();
    private final StringProperty producator = new SimpleStringProperty();
    private final StringProperty imagine = new SimpleStringProperty();

    public MedicamentVM(Medicament m){
        id.set(m.getId());
        denumire.set(m.getDenumire());
        producator.set(m.getProducator());
        imagine.set(m.getImagine());
    }

    public int getId(){ return id.get(); }
    public IntegerProperty idProperty(){ return id; }

    public StringProperty denumireProperty(){ return denumire; }
    public StringProperty producatorProperty(){ return producator; }
    public StringProperty imagineProperty(){ return imagine; }
    public String getDenumire() {
        return denumire.get();
    }

    public String getProducator() {
        return producator.get();
    }

    public String getImagine() {
        return imagine.get();
    }
    @Override
    public String toString() {
        return denumire.get();
    }
}