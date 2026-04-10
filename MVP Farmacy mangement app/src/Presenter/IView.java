package Presenter;

public interface IView {

    int getFarmId();
    String getFarmNume();
    String getFarmAdresa();

    int getMedId();
    String getMedDenumire();
    String getMedProducator();
    String getMedImagine();

    String getSearchName();

    boolean getDisponibilitate();
    String getFilterProducator();
    String getFilterValabilitateDate();

    int getCantitate();
    String getStocValabilitate();

    void showFarmaciiCombo(String[] names);
    void showMedicamenteCombo(String[] names);

    void showFarmaciiTable(String[][] data);
    void showMedicamenteTable(String[][] data);
    void showFiltered(String[][] data);

    int getSelectedIdFromTable();
    String getSelectedImagePath();
    void setMedFields(String den, String prod, String img);
    void openFileChooser();
    void showImage(String path);
    void showMessage(String msg);
}