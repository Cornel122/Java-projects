package Presenter;

import Model.Medicament;
import Model.Farmacie;
import Model.Stoc;
import Model.Repository.MedicamentRepository;
import Model.Repository.FarmacieRepository;
import Model.Repository.StocRepository;

import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Presenter {

    private final IView view;
    private final MedicamentRepository medicamentRepo;
    private final FarmacieRepository farmacieRepo;
    private final StocRepository stocRepo;

    public Presenter(IView view) {
        this.view = view;
        medicamentRepo = new MedicamentRepository();
        farmacieRepo = new FarmacieRepository();
        stocRepo = new StocRepository();
    }

    public void addFarmacie() {
        farmacieRepo.add(new Farmacie(0, view.getFarmNume(), view.getFarmAdresa()));
        view.showMessage("Farmacie adaugata");
        listFarmacii();
    }

    public void updateFarmacie() {
        int id = view.getSelectedIdFromTable();

        farmacieRepo.update(new Farmacie(id,
                view.getFarmNume(),
                view.getFarmAdresa()));

        view.showMessage("Farmacie actualizata");
        listFarmacii();
    }

    public void deleteFarmacie() {
        int id = view.getSelectedIdFromTable();

        farmacieRepo.delete(id);

        view.showMessage("Farmacie stearsa");
        listFarmacii();
    }

    public void listFarmacii() {
        List<Farmacie> list = farmacieRepo.getAll();

        String[][] tableData = new String[list.size()][3];
        String[] comboData = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Farmacie f = list.get(i);
            tableData[i][0] = String.valueOf(f.getId());
            tableData[i][1] = f.getNume();
            tableData[i][2] = f.getAdresa();
            comboData[i] = f.getId() + " - " + f.getNume() + " (" + f.getAdresa() + ")";
        }

        view.showFarmaciiCombo(comboData);
        view.showFarmaciiTable(tableData);
    }

    public void addMedicament() {
        medicamentRepo.add(new Medicament(0,
                view.getMedDenumire(),
                view.getMedProducator(),
                view.getMedImagine()));

        view.showMessage("Medicament adaugat");
        listMedicamente();
    }

    public void updateMedicament() {
        int id = view.getSelectedIdFromTable();

        medicamentRepo.update(new Medicament(id,
                view.getMedDenumire(),
                view.getMedProducator(),
                view.getMedImagine()));

        view.showMessage("Medicament actualizat");
        listMedicamente();
    }

    public void deleteMedicament() {
        int id = view.getSelectedIdFromTable();

        medicamentRepo.delete(id);

        view.showMessage("Medicament sters");
        listMedicamente();
    }

    public void listMedicamente() {
        List<Medicament> list = medicamentRepo.getAllSorted();

        String[][] tableData = new String[list.size()][4];
        String[] comboData = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Medicament m = list.get(i);
            tableData[i][0] = String.valueOf(m.getId());
            tableData[i][1] = m.getDenumire();
            tableData[i][2] = m.getProducator();
            tableData[i][3] = m.getImagine();
            comboData[i] = m.getId() + " - " + m.getDenumire() + " (" + m.getProducator() + ")";
        }

        view.showMedicamenteCombo(comboData);
        view.showMedicamenteTable(tableData);
    }

    public void searchMedicament() {

        if(view.getFarmId() == 0){
            view.showMessage("Selectati farmacia!");
            return;
        }

        List<Stoc> list = stocRepo.searchByDenumire(
                view.getFarmId(),
                view.getSearchName()
        );
        if(list.isEmpty()){
            view.showMessage("Nu s-au gasit medicamente!");
            return;
        }
        showStocList(list);
    }

    public void addStoc() {
        try {
            if(view.getFarmId() == 0 || view.getMedId() == 0){
                view.showMessage("Selectati farmacie si medicament!");
                return;
            }

            Date sqlDate = Date.valueOf(view.getStocValabilitate().trim());

            stocRepo.addStoc(
                    view.getFarmId(),
                    view.getMedId(),
                    view.getCantitate(),
                    view.getDisponibilitate(),
                    sqlDate
            );

            view.showMessage("Stoc adaugat");
            showMedicamenteFarmacie();

        } catch (DateTimeParseException e) {
            view.showMessage("Data invalida! Format: YYYY-MM-DD");
        } catch (Exception e) {
            view.showMessage("Eroare: " + e.getMessage());
        }
    }

    public void showMedicamenteFarmacie() {
        List<Stoc> list = stocRepo.getMedicamenteFarmacie(view.getFarmId());
        showStocList(list);
    }

    public void filterDisponibilitate() {
        List<Stoc> list = stocRepo.filterByDisponibilitate(view.getFarmId(), view.getDisponibilitate());
        if(list.isEmpty()){
            view.showMessage("Nu exista medicamente disponibile!");
            return;
        }
        showStocList(list);
    }

    public void filterProducator() {
        List<Stoc> list = stocRepo.filterByProducator(view.getFarmId(), view.getFilterProducator());

        if(list.isEmpty()){
            view.showMessage("Nu exista medicamente pentru acest producator!");
            return;
        }
        showStocList(list);
    }

    public void filterValabilitate() {
        try {
            Date d = Date.valueOf(view.getFilterValabilitateDate());
            List<Stoc> list = stocRepo.filterByValabilitate(view.getFarmId(), d);
            if(list.isEmpty()){
                view.showMessage("Nu exista medicamente cu aceasta valabilitate!");
                return;
            }
            showStocList(list);

        } catch (Exception e) {
            view.showMessage("Data invalida!");
        }
    }

    private void showStocList(List<Stoc> list) {
        String[][] tableData = new String[list.size()][7];

        for (int i = 0; i < list.size(); i++) {
            Stoc s = list.get(i);

            tableData[i][0] = s.getFarmNume();
            tableData[i][1] = s.getDenumire();
            tableData[i][2] = s.getProducator();
            tableData[i][3] = s.getImagine();
            tableData[i][4] = String.valueOf(s.getCantitate());
            tableData[i][5] = String.valueOf(s.isDisponibilitate());
            tableData[i][6] = s.getValabilitate().toString();
        }

        view.showFiltered(tableData);
    }

    public void chooseImage() {
        view.openFileChooser();
    }

    public void rowSelected() {
        String img = view.getSelectedImagePath();

        if (img == null || img.isEmpty()) return;

        view.setMedFields("", "", img);
        view.showImage(img);
    }
}