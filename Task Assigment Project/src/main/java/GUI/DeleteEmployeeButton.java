package GUI;

import Busniess.TaskManagement;
import DataAccess.DataStore;
import Model.Employee;
import Model.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class DeleteEmployeeButton implements ActionListener {
    private TaskManagement taskManagement;

    public DeleteEmployeeButton(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog("Enter Employee ID to Delete:");
        if (id == null) return;

        try {
            int employeeId = Integer.parseInt(id.trim());

            Map<Employee, List<Task>> taskMap=taskManagement.getTaskMap();
            boolean success=false;
            for(Employee employee:taskMap.keySet()){
                if(employee.getIdEmployee()==employeeId){
                    taskMap.remove(employee);
                    success=true;

                }
            }

            if (success) {
                JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }


            DataStore.saveToFile(taskManagement, "DATA.srs");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
