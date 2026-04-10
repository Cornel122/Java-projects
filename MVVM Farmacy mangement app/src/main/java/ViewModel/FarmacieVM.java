package ViewModel;

import Model.Farmacie;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FarmacieVM {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nume = new SimpleStringProperty();
    private final StringProperty adresa = new SimpleStringProperty();

    public FarmacieVM(Farmacie f){
        id.set(f.getId());
        nume.set(f.getNume());
        adresa.set(f.getAdresa());
    }

    public int getId(){ return id.get(); }
    public IntegerProperty idProperty(){ return id; }

    public StringProperty numeProperty(){ return nume; }
    public StringProperty adresaProperty(){ return adresa; }
    public String getNume() {
        return nume.get();
    }

    public String getAdresa() {
        return adresa.get();
    }
    @Override
    public String toString() {
        return nume.get();
    }
}