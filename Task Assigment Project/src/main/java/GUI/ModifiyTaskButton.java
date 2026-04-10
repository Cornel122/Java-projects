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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifiyTaskButton implements ActionListener {
    private TaskManagement taskManagement;
    private List<Task> tasks;
    public ModifiyTaskButton(TaskManagement taskManagement, List<Task> tasks) {
            this.taskManagement = taskManagement;
            this.tasks = tasks;
    }
    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog("Enter Employee ID");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Employee ID");
            return;
        }

        int idEmployee = Integer.parseInt(id);

        String taskId = JOptionPane.showInputDialog("Enter Task ID");
        if (taskId == null || taskId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Task ID");
            return;
        }

        int idTask = Integer.parseInt(taskId);

        Employee employee = taskManagement.findEmployee(idEmployee);
        if (employee == null) {
            JOptionPane.showMessageDialog(null, "Employee not found");
            return;
        }
        Map<Employee, List<Task>> taskMap = taskManagement.getTaskMap();
        List<Task> employeeTasks = taskMap.get(employee);

        if (employeeTasks == null) {
            JOptionPane.showMessageDialog(null, "No tasks found for this employee");
            return;
        }
//imi actualizez si sirul de taskuri normal ca sa fie identic cu ce assignata in hashmap
        for (Task task : tasks) {
            if (task.getIdTask() == idTask) {
                if (task.getStatusTask().equalsIgnoreCase("Uncompleted"))
                    task.setStatusTask("Completed");
                else
                    task.setStatusTask("Uncompleted");

            }
        }
            int ok=0;
            for(Task task1:employeeTasks) {
                if(task1.getIdTask()==idTask) {
                    if(task1 instanceof ComplexTask) {
                        ok=1;
                         if(task1.getStatusTask().equalsIgnoreCase("Completed")) {
                            task1.setStatusTask("Uncompleted");
                         }
                         else task1.setStatusTask("Completed");
                    }
                }
            }


      if(ok==0)
           taskManagement.modifyTaskStatus(idEmployee, idTask);

        DataStore.saveToFile(tasks, "Task.srs");
        DataStore.saveToFile(taskManagement, "DATA.srs");

    }
}
