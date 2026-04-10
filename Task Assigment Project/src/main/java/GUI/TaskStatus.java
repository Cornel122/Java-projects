package GUI;

import Busniess.TaskManagement;
import Busniess.Utility;
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static Busniess.Utility.calculateTaskStatus;

public class TaskStatus implements ActionListener {
    private TaskManagement taskManagement;
    public TaskStatus(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
    }
    public void actionPerformed(ActionEvent e) {
        Map<String, Map<String, Integer>> taskStatusData = Utility.calculateTaskStatus(taskManagement);


        String[] columnNames = {"Employee Name", "Completed Tasks", "Uncompleted Tasks"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

         //parcurg hashmapul
        for (Map.Entry<String, Map<String, Integer>> entry : taskStatusData.entrySet()) {
            String employeeName = entry.getKey();
            int completed = entry.getValue().getOrDefault("Completed", 0);
            int uncompleted = entry.getValue().getOrDefault("Uncompleted", 0);

            Object[] row = {employeeName, completed, uncompleted};
            model.addRow(row);
        }


        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Task Status");
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


}
