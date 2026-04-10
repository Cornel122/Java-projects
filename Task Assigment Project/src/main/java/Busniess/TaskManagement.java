package Busniess;

import Model.ComplexTask;
import Model.Employee;
import Model.SimpleTask;
import Model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManagement implements Serializable {
    private static final long serialVersionUID = 1L;
     private   HashMap<Employee, List<Task>>taskMap;


    public TaskManagement() {
        taskMap = new HashMap<>();
    }

    public HashMap<Employee, List<Task>> getTaskMap() {
        return taskMap;
    }


    public void addEmployee(Employee e)
    { List<Task> tasks = taskMap.get(e);
        if (tasks == null) {
            tasks = new ArrayList<>();
            taskMap.put(e, tasks);
        }

    }
    //gasesc un employee dupa id
    public Employee findEmployee(int idEmployee) {
        for (Employee employee : taskMap.keySet()) {
            if (employee.getIdEmployee() == idEmployee) {
                return employee;
            }
        }
            return null;

    }
    // adaug in hashmap
    public void assignTaskToEmployee(int idEmployee, Task task) {
        Employee employee = findEmployee(idEmployee);
        taskMap.get(employee).add(task);
    }
  //calculez timpu de lucru numa daca taskul este commplet
    public int calculateEmployeeWorkDuration(int idEmployee){
        List<Task> tasks = taskMap.get(findEmployee(idEmployee));
        int suma=0;
        for(Task t : tasks){
            if(t.checkStatus()==true)
            suma+=t.estimateDuration();

        }
        return suma;
    }
 //parcurg lista de taskuri si le initalizez in functie de alegere iar daca este task complex verific si acolo sa fie complete
    public void modifyTaskStatus(int idEmployee, int idTask) {
        int ok=0;
        List<Task> tasks = taskMap.get(findEmployee(idEmployee));
        if (tasks != null) {
            for (Task task : tasks) {
                if (task.getIdTask() == idTask) {
                    ok = 1;
                    if (task instanceof SimpleTask && task.checkStatus() == false) {
                        task.setStatusTask("Completed");
                    } else
                        task.setStatusTask("Uncompleted");

                    if (task instanceof ComplexTask && task.checkStatus() == true) {
                        task.setStatusTask("Completed");
                    }
                    else if (task instanceof ComplexTask) task.setStatusTask("Uncompleted");
                }
            }


        }
        }



}

