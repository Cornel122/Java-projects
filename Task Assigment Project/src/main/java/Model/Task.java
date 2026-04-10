package Model;

import java.io.Serializable;

public abstract class Task implements Serializable {
     private static final long serialVersionUID = 1L;
    private int idTask;
    private String statusTask;
    public int getIdTask() {
        return idTask;
    }
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }
    public String getStatusTask() {
        return statusTask;

    }
    public void setStatusTask(String statusTask) {

        this.statusTask = statusTask;
    }
    //constructor gol in caz ca am de initializat un obiect
    public Task() {}
    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }
    public abstract int estimateDuration();
    public abstract boolean checkStatus();

}
