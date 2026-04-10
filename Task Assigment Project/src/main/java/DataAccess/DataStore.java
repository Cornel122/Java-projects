package DataAccess;
import Model.Task;

import java.io.*;
import java.util.List;

public class DataStore {
//fac fisier nou in care scriu obicetul
        public static void saveToFile(Object obj, String filename) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(obj);
                System.out.println("Data saved successfully to " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //incarc din fisier obiectul
        public static <T> T loadFromFile(String filename) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                return (T) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

}


