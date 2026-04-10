package ViewModel;

import Model.*;
import Model.Repository.*;
import ViewModel.Commands.RelayCommand;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class MainViewModel {

    private final MedicamentRepository medRepo = new MedicamentRepository();
    private final FarmacieRepository farmRepo = new FarmacieRepository();
    private final StocRepository stocRepo = new StocRepository();

    private StringProperty farmNume = new SimpleStringProperty();
    private StringProperty farmAdresa = new SimpleStringProperty();

    private StringProperty medDenumire = new SimpleStringProperty();
    private StringProperty medProducator = new SimpleStringProperty();
    private StringProperty medImagine = new SimpleStringProperty();

    private IntegerProperty selectedFarmId = new SimpleIntegerProperty();
    private IntegerProperty selectedMedId = new SimpleIntegerProperty();

    private StringProperty search = new SimpleStringProperty();
    private StringProperty filterProd = new SimpleStringProperty();
    private StringProperty filterDate = new SimpleStringProperty();

    private BooleanProperty disponibil = new SimpleBooleanProperty();
    private IntegerProperty cantitate = new SimpleIntegerProperty();
    private StringProperty valabilitate = new SimpleStringProperty();

    private BooleanProperty filterDisponibil = new SimpleBooleanProperty();
    private StringProperty dispFilter = new SimpleStringProperty();

    private StringProperty errorMessage = new SimpleStringProperty();
    public StringProperty errorMessageProperty(){ return errorMessage; }

    private ObjectProperty<FarmacieVM> selectedFarm = new SimpleObjectProperty<>();
    private ObjectProperty<MedicamentVM> selectedMed = new SimpleObjectProperty<>();
    private ObjectProperty<StocVM> selectedStoc = new SimpleObjectProperty<>();

    private StringProperty imagePath = new SimpleStringProperty();
    private StringProperty stocImagePath = new SimpleStringProperty();

    private ObservableList<FarmacieVM> farmacii = FXCollections.observableArrayList();
    private ObservableList<MedicamentVM> medicamente = FXCollections.observableArrayList();
    private ObservableList<StocVM> stocuri = FXCollections.observableArrayList();

    public RelayCommand addFarmCmd, updateFarmCmd, deleteFarmCmd, loadFarmCmd;
    public RelayCommand addMedCmd, updateMedCmd, deleteMedCmd, loadMedCmd;
    public RelayCommand addStocCmd, searchCmd, filterProdCmd, filterDateCmd, filterDispCmd, showStocCmd;

    public MainViewModel() {

        addFarmCmd = new RelayCommand(this::addFarm);
        updateFarmCmd = new RelayCommand(this::updateFarm);
        deleteFarmCmd = new RelayCommand(this::deleteFarm);
        loadFarmCmd = new RelayCommand(this::loadFarm);

        addMedCmd = new RelayCommand(this::addMed);
        updateMedCmd = new RelayCommand(this::updateMed);
        deleteMedCmd = new RelayCommand(this::deleteMed);
        loadMedCmd = new RelayCommand(this::loadMed);

        addStocCmd = new RelayCommand(this::addStoc);
        searchCmd = new RelayCommand(this::search);
        filterProdCmd = new RelayCommand(this::filterProd);
        filterDateCmd = new RelayCommand(this::filterDate);
        filterDispCmd = new RelayCommand(this::filterDisp);
        showStocCmd = new RelayCommand(this::showStoc);

        selectedFarm.addListener((obs, oldV, newV) -> {
            if(newV != null){
                selectedFarmId.set(newV.getId());
                farmNume.set(newV.numeProperty().get());
                farmAdresa.set(newV.adresaProperty().get());
                showStoc();
            }
        });

        selectedMed.addListener((obs, oldV, newV) -> {
            if(newV != null){
                selectedMedId.set(newV.getId());
                medDenumire.set(newV.denumireProperty().get());
                medProducator.set(newV.producatorProperty().get());
                medImagine.set(newV.imagineProperty().get());
                imagePath.set(newV.imagineProperty().get());
            }
        });

        selectedStoc.addListener((obs, o, n) -> {
            if(n != null){
                stocImagePath.set(n.imagineProperty().get());
            }
        });

        dispFilter.addListener((obs, o, n) -> {
            if(n == null) return;
            switch (n){
                case "Disponibile" -> {
                    filterDisponibil.set(true);
                    filterDisp();
                }
                case "Indisponibile" -> {
                    filterDisponibil.set(false);
                    filterDisp();
                }
                default -> showStoc();
            }
        });

        loadFarm();
        loadMed();
    }

    private void addFarm() {
        if(farmNume.get()==null || farmNume.get().isBlank()) {
            errorMessage.set("Numele farmaciei este gol!");
            return;
        }
        farmRepo.add(new Farmacie(0, farmNume.get(), farmAdresa.get()));
        loadFarm();
        errorMessage.set("");
    }

    private void updateFarm() {
        farmRepo.update(new Farmacie(selectedFarmId.get(), farmNume.get(), farmAdresa.get()));
        loadFarm();
        errorMessage.set("");
    }

    private void deleteFarm() {
        farmRepo.delete(selectedFarmId.get());
        loadFarm();
        errorMessage.set("");
    }

    private void loadFarm() {
        farmacii.setAll(farmRepo.getAll().stream().map(FarmacieVM::new).toList());
    }

    private void addMed() {
        if(medDenumire.get() == null || medDenumire.get().isEmpty()) {
            errorMessage.set("Denumire medicament este necesara!");
            return;
        }
        if(medProducator.get() == null || medProducator.get().isEmpty()) {
            errorMessage.set("Producatorul medicamentului este necesar!");
            return;
        }
        medRepo.add(new Medicament(0, medDenumire.get(), medProducator.get(), medImagine.get()));
        loadMed();
        errorMessage.set("");
    }

    private void updateMed() {
        medRepo.update(new Medicament(selectedMedId.get(), medDenumire.get(), medProducator.get(), medImagine.get()));
        loadMed();
        errorMessage.set("");
    }

    private void deleteMed() {
        medRepo.delete(selectedMedId.get());
        loadMed();
        errorMessage.set("");
    }

    private void loadMed() {
        medicamente.setAll(medRepo.getAllSorted().stream().map(MedicamentVM::new).toList());
    }

    private void addStoc() {
        try {
            if(selectedFarmId.get() == 0) {
                errorMessage.set("Selectati o farmacie!");
                return;
            }
            if(selectedMedId.get() == 0) {
                errorMessage.set("Selectati un medicament!");
                return;
            }

            String val = valabilitate.get();
            if(val == null || val.isBlank()) {
                errorMessage.set("Valabilitate is empty!");
                return;
            }

            Date d;
            try {
                d = Date.valueOf(val);
            } catch(IllegalArgumentException e){
                errorMessage.set("Invalid date format! Use yyyy-mm-dd");
                return;
            }

            stocRepo.addStoc(
                    selectedFarmId.get(),
                    selectedMedId.get(),
                    cantitate.get(),
                    disponibil.get(),
                    d
            );

            showStoc();
            errorMessage.set("");

        } catch (Exception e) {
            errorMessage.set("Eroare la adaugarea stocului: " + e.getMessage());
        }
    }

    private void showStoc() {
        stocuri.setAll(
                stocRepo.getMedicamenteFarmacie(selectedFarmId.get())
                        .stream().map(StocVM::new).toList()
        );
    }

    private void search() {
        stocuri.setAll(
                stocRepo.searchByDenumire(selectedFarmId.get(), search.get())
                        .stream().map(StocVM::new).toList()
        );
    }

    private void filterProd() {
        stocuri.setAll(
                stocRepo.filterByProducator(selectedFarmId.get(), filterProd.get())
                        .stream().map(StocVM::new).toList()
        );
    }

    private void filterDate() {
        try {
            if(filterDate.get() == null || filterDate.get().isEmpty()){
                showStoc();
                return;
            }
            Date d = Date.valueOf(filterDate.get());
            stocuri.setAll(
                    stocRepo.filterByValabilitate(selectedFarmId.get(), d)
                            .stream().map(StocVM::new).toList()
            );
        } catch (Exception e) {
            errorMessage.set("Format data invalid! Use yyyy-mm-dd");
        }
    }

    private void filterDisp() {
        stocuri.setAll(
                stocRepo.filterByDisponibilitate(selectedFarmId.get(), filterDisponibil.get())
                        .stream().map(StocVM::new).toList()
        );
    }

    public ObservableList<FarmacieVM> getFarmacii(){ return farmacii; }
    public ObservableList<MedicamentVM> getMedicamente(){ return medicamente; }
    public ObservableList<StocVM> getStocuri(){ return stocuri; }

    public StringProperty farmNumeProperty(){ return farmNume; }
    public StringProperty farmAdresaProperty(){ return farmAdresa; }

    public StringProperty medDenumireProperty(){ return medDenumire; }
    public StringProperty medProducatorProperty(){ return medProducator; }
    public StringProperty medImagineProperty(){ return medImagine; }

    public StringProperty searchProperty(){ return search; }
    public StringProperty filterProdProperty(){ return filterProd; }
    public StringProperty filterDateProperty(){ return filterDate; }

    public BooleanProperty disponibilProperty(){ return disponibil; }
    public IntegerProperty cantitateProperty(){ return cantitate; }
    public StringProperty valabilitateProperty(){ return valabilitate; }

    public ObjectProperty<FarmacieVM> selectedFarmProperty(){ return selectedFarm; }
    public ObjectProperty<MedicamentVM> selectedMedProperty(){ return selectedMed; }
    public ObjectProperty<StocVM> selectedStocProperty(){ return selectedStoc; }

    public StringProperty imagePathProperty(){ return imagePath; }
    public StringProperty stocImagePathProperty(){ return stocImagePath; }

    public StringProperty dispFilterProperty(){ return dispFilter; }
}