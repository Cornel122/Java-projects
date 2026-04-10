package GUI;

import Busniess.TaskManagement;
import DataAccess.DataStore;
import Model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeButton  implements ActionListener{
    private TaskManagement taskManagement;

    public AddEmployeeButton(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog("Enter Employee ID:");
        if (id == null) return;

        String name = JOptionPane.showInputDialog("Enter Employee Name:");
        if (name == null || name.trim().isEmpty()) return;

        try {
            int nouId = Integer.parseInt(id.trim());
            Employee newEmployee = new Employee(nouId, name);
            taskManagement.addEmployee(newEmployee);

            JOptionPane.showMessageDialog(null, "Employee Added Successfully!\nID: " + id + "\nName: " + name);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        DataStore.saveToFile(taskManagement,"DATA.srs");


    }
}
