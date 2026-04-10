package GUI;

import Busniess.TaskManagement;
import DataAccess.DataStore;
import Model.ComplexTask;
import Model.SimpleTask;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTaskButton implements ActionListener {

    private List<Task>taskList=new ArrayList<Task>();

    public AddTaskButton(List<Task>taskList){
        this.taskList=taskList;

    }
    public void actionPerformed(ActionEvent e) {
        String taskType = JOptionPane.showInputDialog("Enter Task Type (Simple/Complex):");
        if (taskType == null || taskType.trim().isEmpty()) return;

        Task task = null;

        try {
            if (taskType.equalsIgnoreCase("Simple")) {

                String taskIdStr = JOptionPane.showInputDialog("Enter Task ID:");
                if (taskIdStr == null || taskIdStr.trim().isEmpty()) return;

                int taskId = Integer.parseInt(taskIdStr.trim());

                String status = "Uncompleted";


                String startHourStr = JOptionPane.showInputDialog("Enter Task Start Hour:");
                if (startHourStr == null || startHourStr.trim().isEmpty()) return;

                int startHour = Integer.parseInt(startHourStr.trim());

                String endHourStr = JOptionPane.showInputDialog("Enter Task End Hour:");
                if (endHourStr == null || endHourStr.trim().isEmpty()) return;

                int endHour = Integer.parseInt(endHourStr.trim());

                task = new SimpleTask(taskId, status, startHour, endHour);
                taskList.add(task);

            } else if ( taskType.equalsIgnoreCase("Complex")) {
                String taskIdStr = JOptionPane.showInputDialog("Enter Task ID:");
                if (taskIdStr == null || taskIdStr.trim().isEmpty()) return;

                int taskId = Integer.parseInt(taskIdStr.trim());

                String status = "Uncompleted";

//verific ce fel de task e pt a a putea adauga mai multe taskuri odata intr-un complex task
                task = new ComplexTask(taskId, status);
                while(true) {
                    String idTask = JOptionPane.showInputDialog("Enter Task ID for filling ComplexTask:");
                    if (idTask == null || idTask.trim().isEmpty()) break;
                    int subTaskId = Integer.parseInt(idTask.trim());
                    for (Task t : taskList) {
                        if (t.getIdTask() == subTaskId) {
                            ((ComplexTask) task).addTask(t);

                        }
                    }
                }
                taskList.add(task);

            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid Task Type entered!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DataStore.saveToFile(taskList,"Task.srs");
            JOptionPane.showMessageDialog(null, "Task Created Successfully!\nTask ID: " + task.getIdTask() + "\nType: " + taskType);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    }
