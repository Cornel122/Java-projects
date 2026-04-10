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
import java.util.Map;
    
public class ViewTaskButton implements ActionListener {
    private List<Task> tasks;

    public ViewTaskButton(List<Task> tasks) {
        this.tasks = tasks;

    }
    public void actionPerformed(ActionEvent e) {
        String[] columnNames = {"Task Type","Task ID", "Task Status", "Estimated Duration"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

      for(Task task : tasks) {
            if(task instanceof SimpleTask) {
          Object[] row = {"Simple",task.getIdTask(), task.getStatusTask(),task.estimateDuration()};
          model.addRow(row);

        }
            else if(task instanceof ComplexTask) {
                Object[] row = {"Complex",task.getIdTask(), task.getStatusTask(), task.estimateDuration()};
                model.addRow(row);
            }
      }



        JTable table = new JTable(model);

        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
       JButton viewComplexTaskButton = new JButton("View Complex Task");
       viewComplexTaskButton.setSize(100, 200);
       viewComplexTaskButton.addActionListener(new ViewComplexTask(tasks));

      panel.add(viewComplexTaskButton);
        JFrame frame = new JFrame("View Tasks");
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(viewComplexTaskButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
