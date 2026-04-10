package View;

import Presenter.IView;
import Presenter.Presenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGui extends JFrame implements IView {

    JTextField farmNume = new JTextField(15);
    JTextField farmAdr = new JTextField(15);

    JTextField medDen = new JTextField(15);
    JTextField medProd = new JTextField(15);
    JTextField medImg = new JTextField(15);

    JButton chooseImg = new JButton("Alege Imaginea");

    JComboBox<String> farmCombo = new JComboBox<>();
    JComboBox<String> medCombo = new JComboBox<>();

    JTextField cantitate = new JTextField(10);
    JTextField valabilitate = new JTextField(10);

    JCheckBox disponibil = new JCheckBox("Disponibil");

    JTextField search = new JTextField(10);
    JTextField filterProd = new JTextField(10);
    JTextField filterDate = new JTextField(10);

    JTable table = new JTable();
    JLabel imageLabel = new JLabel();

    Presenter presenter;

    public MainGui() {
        setTitle("Gestiune Lant Farmacii");
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        presenter = new Presenter(this);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Farmacii", farmPanel());
        tabs.add("Medicamente", medPanel());
        tabs.add("Stoc / Filtrare", stocPanel());
        add(tabs, BorderLayout.NORTH);

        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createTitledBorder("Imagine medicament"));
        imgPanel.setPreferredSize(new Dimension(220, 220));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imgPanel.add(imageLabel, BorderLayout.CENTER);
        add(imgPanel, BorderLayout.EAST);

        table.getSelectionModel().addListSelectionListener(e -> presenter.rowSelected());

        setLocationRelativeTo(null);
        setVisible(true);

        presenter.listFarmacii();
        presenter.listMedicamente();
    }

    private JPanel farmPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Gestionare Farmacii"));

        JPanel fields = new JPanel(new GridLayout(2, 2, 10, 10));
        fields.add(new JLabel("Nume Farmacie"));
        fields.add(farmNume);
        fields.add(new JLabel("Adresa"));
        fields.add(farmAdr);
        panel.add(fields, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout());
        JButton add = new JButton("Adaugare");
        JButton update = new JButton("Actualizare");
        JButton delete = new JButton("Stergere");
        JButton list = new JButton("Afisare lista farmacii");

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);
        buttons.add(list);

        panel.add(buttons, BorderLayout.SOUTH);

        add.addActionListener(e -> presenter.addFarmacie());
        update.addActionListener(e -> presenter.updateFarmacie());
        delete.addActionListener(e -> presenter.deleteFarmacie());
        list.addActionListener(e -> presenter.listFarmacii());

        return panel;
    }

    private JPanel medPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Gestionare Medicamente"));

        JPanel fields = new JPanel(new GridLayout(3, 2, 10, 10));
        fields.add(new JLabel("Denumire"));
        fields.add(medDen);
        fields.add(new JLabel("Producator"));
        fields.add(medProd);
        fields.add(new JLabel("Imagine"));
        fields.add(medImg);
        panel.add(fields, BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        top.add(chooseImg);
        panel.add(top, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new FlowLayout());
        JButton add = new JButton("Adaugare");
        JButton update = new JButton("Actualizare");
        JButton delete = new JButton("Stergere");
        JButton list = new JButton("Afisare lista medicamente");

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);
        buttons.add(list);

        panel.add(buttons, BorderLayout.SOUTH);

        chooseImg.addActionListener(e -> presenter.chooseImage());

        add.addActionListener(e -> presenter.addMedicament());
        update.addActionListener(e -> presenter.updateMedicament());
        delete.addActionListener(e -> presenter.deleteMedicament());
        list.addActionListener(e -> presenter.listMedicamente());

        return panel;
    }

    private JPanel stocPanel() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JPanel selectFarm = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        selectFarm.setBorder(BorderFactory.createTitledBorder("Selectare Farmacie"));
        selectFarm.add(new JLabel("Farmacie:"));
        selectFarm.add(farmCombo);
        main.add(selectFarm);

        JPanel stoc = new JPanel(new GridLayout(3, 2, 10, 10));
        stoc.setBorder(BorderFactory.createTitledBorder("Adaugare Stoc"));

        stoc.add(new JLabel("Medicament:"));
        stoc.add(medCombo);
        stoc.add(new JLabel("Cantitate:"));
        stoc.add(cantitate);
        stoc.add(new JLabel("Valabilitate (YYYY-MM-DD):"));
        stoc.add(valabilitate);

        JPanel disponibilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox disponibilAdd = new JCheckBox("Disponibil");
        JButton addStoc = new JButton("Adaugare Stoc");
        disponibilPanel.add(disponibilAdd);
        disponibilPanel.add(addStoc);

        addStoc.addActionListener(e -> {
            disponibil.setSelected(disponibilAdd.isSelected());
            presenter.addStoc();
        });

        main.add(stoc);
        main.add(disponibilPanel);

        JPanel filter = new JPanel(new GridLayout(5, 4, 10, 10));
        filter.setBorder(BorderFactory.createTitledBorder("Filtrare / Cautare"));

        JButton searchBtn = new JButton("Cautare");
        JButton fProd = new JButton("Filtrare Producator");
        JButton fDate = new JButton("Filtrare Valabilitate");
        JButton fDisp = new JButton("Filtreaza disponibilitate");
        JButton showFarm = new JButton("Medicamente din Farmacie");
        JCheckBox disponibilFilter = new JCheckBox("Doar disponibile");

        filter.add(new JLabel("Cautare Medicament"));
        filter.add(search);
        filter.add(searchBtn);
        filter.add(new JLabel());

        filter.add(new JLabel("Producator"));
        filter.add(filterProd);
        filter.add(fProd);
        filter.add(new JLabel());

        filter.add(new JLabel("Valabilitate <"));
        filter.add(filterDate);
        filter.add(fDate);
        filter.add(new JLabel());

        filter.add(new JLabel("Disponibilitate"));
        filter.add(disponibilFilter);
        filter.add(fDisp);
        filter.add(new JLabel());

        filter.add(showFarm);
        filter.add(new JLabel());
        filter.add(new JLabel());
        filter.add(new JLabel());

        searchBtn.addActionListener(e -> presenter.searchMedicament());
        fProd.addActionListener(e -> presenter.filterProducator());
        fDate.addActionListener(e -> presenter.filterValabilitate());
        fDisp.addActionListener(e -> {
            disponibil.setSelected(disponibilFilter.isSelected());
            presenter.filterDisponibilitate();
        });
        showFarm.addActionListener(e -> presenter.showMedicamenteFarmacie());

        main.add(filter);

        return main;
    }

    public int getFarmId() {
        String selected = (String) farmCombo.getSelectedItem();
        return selected != null ? Integer.parseInt(selected.split(" - ")[0]) : 0;
    }

    public int getMedId() {
        String selected = (String) medCombo.getSelectedItem();
        return selected != null ? Integer.parseInt(selected.split(" - ")[0]) : 0;
    }

    public String getSelectedImagePath() {
        int row = table.getSelectedRow();
        if (row < 0) return "";

        try {
            int col = table.getColumn("Imagine").getModelIndex();
            return table.getValueAt(row, col).toString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getFarmNume() { return farmNume.getText(); }
    public String getFarmAdresa() { return farmAdr.getText(); }
    public String getMedDenumire() { return medDen.getText(); }
    public String getMedProducator() { return medProd.getText(); }
    public String getMedImagine() { return medImg.getText(); }
    public String getSearchName() { return search.getText(); }
    public boolean getDisponibilitate() { return disponibil.isSelected(); }
    public String getFilterProducator() { return filterProd.getText(); }
    public String getFilterValabilitateDate() { return filterDate.getText(); }
    public String getStocValabilitate() { return valabilitate.getText(); }

    public int getCantitate() {
        return cantitate.getText().isEmpty() ? 0 : Integer.parseInt(cantitate.getText());
    }

    public void showFarmaciiCombo(String[] names) {
        farmCombo.setModel(new DefaultComboBoxModel<>(names));
    }

    public void showMedicamenteCombo(String[] names) {
        medCombo.setModel(new DefaultComboBoxModel<>(names));
    }

    public void showFarmaciiTable(String[][] data) {
        table.setModel(new DefaultTableModel(data, new String[]{"ID", "Nume", "Adresa"}));
    }

    public void showMedicamenteTable(String[][] data) {
        table.setModel(new DefaultTableModel(data, new String[]{"ID", "Denumire", "Producator", "Imagine"}));
    }

    public void showFiltered(String[][] data) {
        table.setModel(new DefaultTableModel(data,
                new String[]{"Farmacie", "Medicament", "Producator", "Imagine", "Cantitate", "Disponibil", "Valabilitate"}));
    }

    public void setMedFields(String den, String prod, String img) {
        medDen.setText(den);
        medProd.setText(prod);
        medImg.setText(img);
    }

    public void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(this);
        String path = res == JFileChooser.APPROVE_OPTION
                ? chooser.getSelectedFile().getAbsolutePath()
                : "";

        medImg.setText(path);
        showImage(path);
    }

    public void showImage(String path) {
        if (path == null || path.isEmpty()) return;
        imageLabel.setIcon(new ImageIcon(
                new ImageIcon(path).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)
        ));
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public int getSelectedIdFromTable() {
        int row = table.getSelectedRow();
        if (row < 0) return 0;
        try {
            return Integer.parseInt(table.getValueAt(row, 0).toString());
        } catch (Exception e) {
            return 0;
        }
    }
}