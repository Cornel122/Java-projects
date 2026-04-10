    package GUI;

    import DataAccess.DataStore;
    import Model.ComplexTask;
    import Model.Task;

    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.List;

    public class DeleteTaskButton implements ActionListener {
        private List<Task> tasks;

        public DeleteTaskButton(List<Task> tasks) {
            this.tasks = tasks;
        }

        public void actionPerformed(ActionEvent e) {
            String id = JOptionPane.showInputDialog("Enter Task ID");
            int idTask = Integer.parseInt(id);

                int ok = 0;

            Task deSters = null;
            for(Task task:tasks) {
                    if (task.getIdTask() == idTask) {
                        ok = 1;
                        deSters = task;
                    }
                }
            if(ok==1)
                tasks.remove(deSters);
            DataStore.saveToFile(tasks,"Task.srs");

        }
    }
