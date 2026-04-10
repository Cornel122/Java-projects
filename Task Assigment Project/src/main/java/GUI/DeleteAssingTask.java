package GUI;

import Busniess.TaskManagement;
import DataAccess.DataStore;
import Model.Employee;
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class DeleteAssingTask implements ActionListener {
    private TaskManagement taskManagement;
    public DeleteAssingTask(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
    }
    public void actionPerformed(ActionEvent e) {
        String id= JOptionPane.showInputDialog("Enter Employee ID");
        int idEmployee = Integer.parseInt(id);
        Map<Employee, List<Task>> taskMap=taskManagement.getTaskMap();
        List<Task> tasks=taskMap.get(taskManagement.findEmployee(idEmployee));
        String idTask=JOptionPane.showInputDialog("Enter Task ID");
        int idTaskInt = Integer.parseInt(idTask);
        Task deSters=null;
        for(Task task:tasks) {
            if(task.getIdTask()==idTaskInt)
                   deSters=task;
        }
        tasks.remove(deSters
        );
        DataStore.saveToFile(taskManagement,"DATA.srs");
    }
}
