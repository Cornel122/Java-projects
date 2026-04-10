package ViewModel;

import Model.Stoc;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class StocVM {

    private final StringProperty farm = new SimpleStringProperty();
    private final StringProperty den = new SimpleStringProperty();
    private final StringProperty prod = new SimpleStringProperty();
    private final IntegerProperty cant = new SimpleIntegerProperty();
    private final BooleanProperty disp = new SimpleBooleanProperty();
    private final StringProperty val = new SimpleStringProperty();
    private final StringProperty imagine = new SimpleStringProperty();

    public StocVM(Stoc s){
        farm.set(s.getFarmNume());
        den.set(s.getDenumire());
        prod.set(s.getProducator());
        cant.set(s.getCantitate());
        disp.set(s.isDisponibilitate());
        val.set(s.getValabilitate().toString());
        imagine.set(s.getImagine());
    }

    public StringProperty farmProperty(){ return farm; }
    public StringProperty denProperty(){ return den; }
    public StringProperty prodProperty(){ return prod; }
    public IntegerProperty cantProperty(){ return cant; }
    public BooleanProperty dispProperty(){ return disp; }
    public StringProperty valProperty(){ return val; }
    public StringProperty imagineProperty(){ return imagine; }
    public String getImagine() {
        return imagine.get();
    }
}