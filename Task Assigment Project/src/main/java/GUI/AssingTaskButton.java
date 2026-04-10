package GUI;

import Busniess.TaskManagement;
import DataAccess.DataStore;
import Model.ComplexTask;
import Model.Employee;
import Model.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssingTaskButton implements ActionListener {
    private TaskManagement taskManagement;
    private List<Task> taskList;
    public AssingTaskButton(TaskManagement taskManagement,List<Task> taskList) {
        this.taskList=taskList;
        this.taskManagement=taskManagement;

    }
    public void actionPerformed(ActionEvent e)  {
        String id = JOptionPane.showInputDialog("Enter Employee ID:");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Employee ID.");
            return;
        }

        int idEmployee;
        try {
            idEmployee = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Employee ID must be a number.");
            return;
        }
        String taskId = JOptionPane.showInputDialog("Enter Task ID:");
        if (taskId == null || taskId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Task ID.");
            return;
        }

        int idTask;
        try {
            idTask = Integer.parseInt(taskId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Task ID must be a number.");
            return;
        }
        //parcurg lista si apelez meotda
        for (Task task : taskList) {
            if (task.getIdTask() == idTask) {
                taskManagement.assignTaskToEmployee(idEmployee, task);
            }

        }
        DataStore.saveToFile(taskList, "Task.srs");
        DataStore.saveToFile(taskManagement, "DATA.srs");
}
}
