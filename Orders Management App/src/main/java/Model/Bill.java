package Model;

import java.time.LocalDateTime;
/**
 * Clasa bill este o clasa imutabila.
 * <p>
 * Clasa contine informatii despre numele clientului,produsului,cantitatea,pretul total si data efectuarii comenzii
 * datele sunt salvate intr-un tabel log in baza de date
 * </p>
 *
 * @param billId       id
 * @param clientName   numele clientului
 * @param productName  numele produsului
 * @param quantity     cantitatea
 * @param totalPrice   prețul total
 * @param data         data și ora
 */

public record Bill(
        int billId,
        String clientName,
        String productName,
        int quantity,
        float totalPrice,
        LocalDateTime data
) {}