package GUI;

import Model.ComplexTask;
import Model.SimpleTask;
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewComplexTask implements ActionListener {
 private List<Task> tasks;
 public ViewComplexTask(List<Task> tasks) {
     this.tasks = tasks;
 }

            @Override
            public void actionPerformed(ActionEvent e) {

                String input = JOptionPane.showInputDialog("Enter Complex Task ID:");


                if (input == null || input.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Task ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int taskId = Integer.parseInt(input.trim());

                    ComplexTask selectedTask = null;
                    for (Task task : tasks) {
                        if (task instanceof ComplexTask && task.getIdTask() == taskId) {
                            selectedTask = (ComplexTask) task;
                            break;
                        }
                    }

                    if (selectedTask == null) {
                        JOptionPane.showMessageDialog(null, "No Complex Task found with ID: " + taskId, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String[] columnNames = {"Task Type", "Task ID", "Task Status", "Estimated Duration"};
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                    for (Task subTask : selectedTask.getTaskuri()) {
                        String taskType = (subTask instanceof ComplexTask) ? "Complex" : "Simple";
                        Object[] row = {taskType, subTask.getIdTask(), subTask.getStatusTask(), subTask.estimateDuration()};
                        model.addRow(row);
                    }

                    JTable table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);

                    JFrame frame = new JFrame("Complex Task Details");
                    frame.setSize(800, 600);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLayout(new BorderLayout());

                    frame.add(scrollPane, BorderLayout.CENTER);
                    frame.setVisible(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Task ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }




