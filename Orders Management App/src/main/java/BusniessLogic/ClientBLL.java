package BusniessLogic;

import DataAcess.ClientDAO;
import Model.Client;

import java.util.List;

/**
 * Clasa Business Logic pentru entitatea Client.
 * Se ocupă de validarea și gestionarea logicii aplicației pentru clienți.
 */
public class ClientBLL {
    ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * Returnează toți clienții din baza de date.
     * @return lista cu clienți
     */
    public List<Client> getAllClients() throws Exception {
        return clientDAO.findAll();
    }

    /**
     * Adaugă un client nou după validare.
     * @param client clientul de adăugat
     * @throws IllegalArgumentException dacă validarea eșuează
     */
    public void addClient(Client client) throws Exception {
        valideazaClient(client);
        clientDAO.insert(client);
    }

    /**
     * Actualizează un client după validare.
     * @param client clientul actualizat
     * @throws IllegalArgumentException dacă validarea eșuează
     */
    public void updateClient(Client client) throws Exception {
        valideazaClient(client);
        clientDAO.update(client);
    }

    /**
     * Șterge un client din baza de date după ID.
     * @param id id-ul clientului de șters
     */
    public void deleteClient(int id) throws Exception {
        clientDAO.delete(id);
    }

    /**
     * Validează atributele unui client.
     * @param client clientul de validat
     * @throws IllegalArgumentException dacă numele este gol sau null
     */
    private void valideazaClient(Client client) {
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new IllegalArgumentException("Numele clientului nu poate fi gol.");
        }
        if (client.getId()<=0) {
            throw new IllegalArgumentException("idu trebuie sa fie pozitiv");
        }
    }
}
