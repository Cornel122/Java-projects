package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComplexTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
  private List<Task> taskuri=new ArrayList<Task>();
  public List<Task> getTaskuri() {
      return taskuri;
  }
    public ComplexTask(int idTask, String StatusTask) {
        super(idTask,StatusTask);

    }
   public void addTask(Task task ){
        taskuri.add(task);

   }
    public void deleteTask(Task task ){
        taskuri.remove(task);
    }
    //verific daca starea unui Task
    public boolean checkStatus(){
        for(Task task : taskuri){
            if(task.getStatusTask().equalsIgnoreCase("Uncompleted"))
                return false;
        }
        return true;
    }
    public int estimateDuration(){
        int suma=0;
        for(Task task : taskuri){
            suma+=task.estimateDuration();
        }
     return suma;

    }

}
