package Busniess;
import Model.Employee;
import Model.Task;

import java.util.*;

public class Utility  {
    //I-au lista de employee si daca vad ca metoda estimateduration este mai mare ca 40 adaug in alta lista pe care o sortez si returnez
    public static List<Employee> filterEmployees(TaskManagement taskManagement) {
        List<Employee> employees = new ArrayList<Employee>();
        HashMap<Employee,List<Task>> tm= taskManagement.getTaskMap();
        for(Employee employee :tm.keySet()) {
            if(taskManagement.calculateEmployeeWorkDuration(employee.getIdEmployee())>40)
                employees.add(employee);
        }
        employees.sort(new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                if(taskManagement.calculateEmployeeWorkDuration(o1.getIdEmployee())>taskManagement.calculateEmployeeWorkDuration(o2.getIdEmployee()))
                    return 1;
                else if(taskManagement.calculateEmployeeWorkDuration(o1.getIdEmployee())<taskManagement.calculateEmployeeWorkDuration(o2.getIdEmployee()))

                    return -1;
                else return 0;
            }
        });
        return employees;
    }



//Parcurg lista de employees din hashmap si  apoi pt fiecare parcurg lista de taskuri si contorizez cele completate si cele necompletate
    // dupa le pun in alt map si l returnez
        public static Map<String, Map<String, Integer>> calculateTaskStatus(TaskManagement taskManagement) {
            Map<String, Map<String, Integer>> employeeTaskStatus = new HashMap<>();
            Map<Employee, List<Task>> taskMap = taskManagement.getTaskMap();

            for (Employee employee : taskMap.keySet()) {
                List<Task> tasks = taskMap.get(employee);
                int completed = 0;
                int incompleted = 0;

                for (Task task : tasks) {
                    if ("Completed".equals(task.getStatusTask())) {
                        completed++;
                    } else {
                        incompleted++;
                    }
                }
                Map<String, Integer> statusCount = new HashMap<>();
                statusCount.put("Completed", completed);
                statusCount.put("Uncompleted", incompleted);

                employeeTaskStatus.put(employee.getName(), statusCount);
            }
            return employeeTaskStatus;
        }
    }


