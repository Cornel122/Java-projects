package BusniessLogic;

import DataAcess.ProductDAO;
import Model.Product;

import java.util.List;

/**
 * Clasa Business Logic Layer pentru entitatea Product.
 * Gestionează validările și regulile de business înainte de accesarea DAO-ului.
 */
public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Returnează toate produsele.
     * @return listă cu toate produsele
     */
    public List<Product> getAllProducts() throws Exception {
        return productDAO.findAll();
    }

    /**
     * Adaugă un produs nou după validare.
     * @param product produsul de adăugat
     * @throws IllegalArgumentException dacă validarea eșuează
     */
    public void addProduct(Product product) throws Exception {
        valideazaProdus(product);
        productDAO.insert(product);
    }

    /**
     * Actualizează un produs după validare.
     * @param product produsul de actualizat
     * @throws IllegalArgumentException dacă validarea eșuează
     */
    public void updateProduct(Product product) throws Exception {
        valideazaProdus(product);
        productDAO.update(product);
    }

    /**
     * Șterge un produs după id.
     * @param id id-ul produsului
     */
    public void deleteProduct(int id) throws Exception {
        productDAO.delete(id);
    }

    /**
     * Validează datele unui produs.
     * @param product produsul de validat
     */
    private void valideazaProdus(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Numele produsului nu poate fi gol");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Prețul trebuie să fie pozitiv");
        }
        if (product.getQuantityInStock() < 0) {
            throw new IllegalArgumentException("Cantitatea în stoc nu poate fi negativă");
        }
    }
}
