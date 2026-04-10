package Model;

import java.time.LocalDateTime;
/**
 * Reprezintă o comandă efectuată de un client.
 * <p>
 * Clasa conține informații despre client, produsul comandat, cantitate,
 * data comenzii și valoarea totală a acesteia.
 * </p>
 */
public class Orders {
    private int orderId;
    private int clientId;
    private String clientName;
    private int productId;
    private String productName;
    private int quantity;
    private LocalDateTime orderDate;
    private float totalAmount;

    public Orders() {}
    /**
     * Constructor cu parametri pentru inițializarea unei comenzi.
     *
     * @param orderId     id-ul comenzii
     * @param clientId    id-ul clientului
     * @param clientName  Numele clientului
     * @param productId   id-ul produsului
     * @param productName Numele produsului
     * @param quantity    Cantitatea
     * @param orderDate   Data comenzii
     * @param productPrice Prețul
     */
    public Orders(int orderId, int clientId, String clientName,
                  int productId, String productName, int quantity, LocalDateTime orderDate, float productPrice) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = calculateTotal(productPrice);
    }
    /**
     * Calculează totalul comenzii în funcție de prețul produsului.
     *
     * @param productPrice Prețul
     * @return Valoarea totală
     */
    private float calculateTotal(float productPrice) {
        return productPrice * quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    /**
     * Afisare comanda
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
