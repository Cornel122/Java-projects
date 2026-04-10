package View;

import ViewModel.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;

public class MainView {

    @FXML private TextField farmNume, farmAdr;
    @FXML private TextField medDen, medProd, medImg;
    @FXML private TextField search, filterProd, filterDate, cantitate, valabilitate;
    @FXML private CheckBox disponibil;

    @FXML private TableView<FarmacieVM> farmTable;
    @FXML private TableView<MedicamentVM> medTable;
    @FXML private TableView<StocVM> stocTable;

    @FXML private ComboBox<FarmacieVM> farmCombo;
    @FXML private ComboBox<MedicamentVM> medCombo;

    @FXML private ImageView imageView;
    @FXML private ImageView stocImageView;

    @FXML private ComboBox<String> dispFilter;

    @FXML private TableColumn<FarmacieVM, Number> colFarmId;
    @FXML private TableColumn<FarmacieVM, String> colFarmNume;
    @FXML private TableColumn<FarmacieVM, String> colFarmAdr;

    @FXML private TableColumn<MedicamentVM, Number> colMedId;
    @FXML private TableColumn<MedicamentVM, String> colMedDen;
    @FXML private TableColumn<MedicamentVM, String> colMedProd;
    @FXML private TableColumn<MedicamentVM, String> colMedImg;

    @FXML private TableColumn<StocVM, String> colFarm;
    @FXML private TableColumn<StocVM, String> colDen;
    @FXML private TableColumn<StocVM, String> colProd;
    @FXML private TableColumn<StocVM, Number> colCant;
    @FXML private TableColumn<StocVM, Boolean> colDisp;
    @FXML private TableColumn<StocVM, String> colVal;
    @FXML private Label errorLabel;
    private MainViewModel vm;

    public void initialize() {

        vm = new MainViewModel();

        colFarmId.setCellValueFactory(d -> d.getValue().idProperty());
        colFarmNume.setCellValueFactory(d -> d.getValue().numeProperty());
        colFarmAdr.setCellValueFactory(d -> d.getValue().adresaProperty());

        colMedId.setCellValueFactory(d -> d.getValue().idProperty());
        colMedDen.setCellValueFactory(d -> d.getValue().denumireProperty());
        colMedProd.setCellValueFactory(d -> d.getValue().producatorProperty());
        colMedImg.setCellValueFactory(d -> d.getValue().imagineProperty());

        colFarm.setCellValueFactory(d -> d.getValue().farmProperty());
        colDen.setCellValueFactory(d -> d.getValue().denProperty());
        colProd.setCellValueFactory(d -> d.getValue().prodProperty());
        colCant.setCellValueFactory(d -> d.getValue().cantProperty());
        colDisp.setCellValueFactory(d -> d.getValue().dispProperty());
        colVal.setCellValueFactory(d -> d.getValue().valProperty());

        farmNume.textProperty().bindBidirectional(vm.farmNumeProperty());
        farmAdr.textProperty().bindBidirectional(vm.farmAdresaProperty());

        medDen.textProperty().bindBidirectional(vm.medDenumireProperty());
        medProd.textProperty().bindBidirectional(vm.medProducatorProperty());
        medImg.textProperty().bindBidirectional(vm.medImagineProperty());

        search.textProperty().bindBidirectional(vm.searchProperty());
        filterProd.textProperty().bindBidirectional(vm.filterProdProperty());
        filterDate.textProperty().bindBidirectional(vm.filterDateProperty());
        valabilitate.textProperty().bindBidirectional(vm.valabilitateProperty());
        cantitate.textProperty().bindBidirectional(vm.cantitateProperty(), new NumberStringConverter());

        disponibil.selectedProperty().bindBidirectional(vm.disponibilProperty());

        errorLabel.textProperty().bind(vm.errorMessageProperty());

        farmTable.setItems(vm.getFarmacii());
        medTable.setItems(vm.getMedicamente());
        stocTable.setItems(vm.getStocuri());
        medTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> vm.selectedMedProperty().set(newV)
        );
        farmCombo.setItems(vm.getFarmacii());
        medCombo.setItems(vm.getMedicamente());

        farmCombo.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> vm.selectedFarmProperty().set(newV)
        );
        farmTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> vm.selectedFarmProperty().set(newV)
        );

        medCombo.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> vm.selectedMedProperty().set(newV)
        );

        stocTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, o, n) -> vm.selectedStocProperty().set(n)
        );

        dispFilter.getItems().addAll("Toate", "Disponibile", "Indisponibile");
        dispFilter.valueProperty().bindBidirectional(vm.dispFilterProperty());
        dispFilter.setValue("Toate");

        vm.imagePathProperty().addListener((obs, o, n) ->
                imageView.setImage(n == null ? null : new Image("file:" + n))
        );
        vm.stocImagePathProperty().addListener((obs, o, n) ->
                stocImageView.setImage(n == null ? null : new Image("file:" + n))
        );

        vm.loadFarmCmd.execute();
        vm.loadMedCmd.execute();
    }

    @FXML public void addFarm(){ vm.addFarmCmd.execute(); }
    @FXML public void updateFarm(){ vm.updateFarmCmd.execute(); }
    @FXML public void deleteFarm(){ vm.deleteFarmCmd.execute(); }
    @FXML public void loadFarm(){ vm.loadFarmCmd.execute(); }

    @FXML public void addMed(){ vm.addMedCmd.execute(); }
    @FXML public void updateMed(){ vm.updateMedCmd.execute(); }
    @FXML public void deleteMed(){ vm.deleteMedCmd.execute(); }
    @FXML public void loadMed(){ vm.loadMedCmd.execute(); }

    @FXML public void addStoc(){ vm.addStocCmd.execute(); }
    @FXML public void search(){ vm.searchCmd.execute(); }
    @FXML public void filterProd(){ vm.filterProdCmd.execute(); }
    @FXML public void filterDate(){ vm.filterDateCmd.execute(); }
    @FXML public void filterDisp(){ vm.filterDispCmd.execute(); }
    @FXML public void showStoc(){ vm.showStocCmd.execute(); }
}