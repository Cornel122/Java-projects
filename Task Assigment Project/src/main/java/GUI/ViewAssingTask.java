package GUI;

import Busniess.TaskManagement;
import Model.ComplexTask;
import Model.Employee;
import Model.SimpleTask;
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ViewAssingTask implements ActionListener {
    private TaskManagement taskManagement;
    public ViewAssingTask(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
    }
    public void actionPerformed(ActionEvent e) {
        String id= JOptionPane.showInputDialog("Enter Employee ID");

        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Employee ID. Please enter a valid ID.");
            return;
        }
        int idEmployee;
        try {
            idEmployee = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Employee ID. Please enter a valid number.");
            return;
        }
        String[] columnNames = {"Id Employee","Task Type","Task ID", "Task Status", "Estimated Duration","Employee Work Duration"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Map<Employee,List<Task>> taskMap=taskManagement.getTaskMap();
        List<Task> tasks=taskMap.get(taskManagement.findEmployee(idEmployee));
        for(Task task : tasks) {
            if(task instanceof SimpleTask) {
                Object[] row = {idEmployee,"Simple", task.getIdTask(),task.getStatusTask(),task.estimateDuration(), taskManagement.calculateEmployeeWorkDuration(idEmployee)};
                model.addRow(row);

            }
            else if(task instanceof ComplexTask) {
                int  idComplexTask = task.getIdTask();
                Object[] row = {idEmployee,"Complex", task.getIdTask(),task.getStatusTask(), task.estimateDuration(),taskManagement.calculateEmployeeWorkDuration(idEmployee)};

                model.addRow(row);
            }
        }



        JTable table = new JTable(model);

        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        JButton viewComplexTaskButton = new JButton("View Complex Task");
        viewComplexTaskButton.setSize(100, 200);
        viewComplexTaskButton.addActionListener(new ViewComplexTask(tasks));
//am mai facut un buton pt a vedea continutul unui task complex
        panel.add(viewComplexTaskButton);
        JFrame frame = new JFrame("View Tasks");
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(viewComplexTaskButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
