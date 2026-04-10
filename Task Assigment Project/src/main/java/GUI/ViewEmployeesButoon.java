package GUI;

import Busniess.TaskManagement;
import Model.Employee;
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ViewEmployeesButoon implements ActionListener {
    TaskManagement tm ;
    public ViewEmployeesButoon(TaskManagement taskManagement) {
        this.tm = taskManagement;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columnNames = {"Employee ID", "Employee Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Map<Employee, List<Task>> taskMap=tm.getTaskMap();

        for(Employee employee: taskMap.keySet()){
            Object[] row = {employee.getIdEmployee(), employee.getName()};
            model.addRow(row);
        }

        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        JFrame tableFrame = new JFrame("Employee List");
        tableFrame.setSize(400, 300);
        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.setVisible(true);
    }
}
