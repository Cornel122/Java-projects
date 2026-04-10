package Model;

/**
 * Reprezintă un produs disponibil în stoc.
 * <p>
 * Clasa conține informații despre idu  produsului, nume, preț
 * și cantitatea .
 * </p>
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private int quantityInStock;


public Product() {}
    /**
     * Constructor cu parametri pentru inițializarea unui produs.
     *
     * @param id              id-ul produsului
     * @param name            Numele produsului
     * @param price           Prețul
     * @param quantityInStock Cantitatea
     */
    public Product(int id, String name, float price, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    /**
     * Verifică dacă produsul este disponibil în stoc.
     *
     * @return {@code true} dacă produsul este disponibil, altfel {@code false}
     */

    public boolean isAvailable() {
        return quantityInStock > 0;
    }

/**
 * Afisare produs
 *
 * @return string
 */

    @Override
    public String toString() {
        return id + " - " + name + " | Price: " + price + " | Stock: " + quantityInStock;
    }
}
