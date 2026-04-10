package GUI;

import Busniess.TaskManagement;
import Busniess.Utility;
import Model.Employee;
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterEmployess implements ActionListener {
 private TaskManagement taskManagement;
 public FilterEmployess(TaskManagement taskManagement) {
     this.taskManagement = taskManagement;
 }
 public void actionPerformed(ActionEvent e) {
     List<Employee> employeeList= Utility.filterEmployees(taskManagement);

     String[] columnNames = {"Employee ID", "Employee Name","WorkDuration"};
     DefaultTableModel model = new DefaultTableModel(columnNames, 0);

     for(Employee employee: employeeList){
         Object[] row = {employee.getIdEmployee(), employee.getName(),taskManagement.calculateEmployeeWorkDuration(employee.getIdEmployee())};
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
