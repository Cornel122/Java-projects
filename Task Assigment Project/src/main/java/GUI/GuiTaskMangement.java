package GUI;
import Busniess.TaskManagement;
import DataAccess.DataStore;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GuiTaskMangement {
    //fac obiecte si incarc din fisiser
private  TaskManagement taskManagement= DataStore.loadFromFile("DATA.srs");
private List<Task> taskList=DataStore.loadFromFile("Task.srs");


    public GuiTaskMangement() {
        JFrame frame = new JFrame("Task Management System");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 3));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton deleteEmployeeButton = new JButton("Remove Employee");
        JButton addTaskButton = new JButton("Add Task");
        JButton assignTaskButton = new JButton("Assign Task");
        JButton viewEmployeesButton = new JButton("View Employees");
        JButton viewTasksButton = new JButton("View Tasks");
        JButton deleteTaskButton = new JButton("Delete Task");
        JButton viewAssignTask = new JButton("View Assing Tasks");
        JButton deleteAssignedTask=new JButton("Delete Assigned Task");
        JButton modifyTaskButton = new JButton("Modify Task Status");
        JButton filterEmployeesButton = new JButton("Filter Employees");
        JButton taskStatus=new JButton("Task Status");
        assignTaskButton.addActionListener(new AssingTaskButton (taskManagement,taskList));
        addEmployeeButton.addActionListener(new AddEmployeeButton(taskManagement));
        viewEmployeesButton.addActionListener(new ViewEmployeesButoon(taskManagement));
        deleteEmployeeButton.addActionListener(new DeleteEmployeeButton(taskManagement));
        addTaskButton.addActionListener(new AddTaskButton(taskList));
        viewTasksButton.addActionListener(new ViewTaskButton(taskList));
        deleteTaskButton.addActionListener(new DeleteTaskButton(taskList));
        viewAssignTask.addActionListener(new ViewAssingTask(taskManagement));
        deleteAssignedTask.addActionListener(new DeleteAssingTask(taskManagement));
        modifyTaskButton.addActionListener(new ModifiyTaskButton(taskManagement,taskList));
        filterEmployeesButton.addActionListener(new FilterEmployess(taskManagement));
        taskStatus.addActionListener(new TaskStatus(taskManagement));

        panel.add(addEmployeeButton);
        panel.add(viewEmployeesButton);
        panel.add(deleteEmployeeButton);
        panel.add(assignTaskButton);
        panel.add(viewAssignTask);
        panel.add(deleteAssignedTask);
        panel.add(addTaskButton);
        panel.add(viewTasksButton);
        panel.add(deleteTaskButton);
        panel.add(modifyTaskButton);
        panel.add(taskStatus);
     panel.add(filterEmployeesButton);
        frame.add(panel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
    new  GuiTaskMangement();


    }
}


