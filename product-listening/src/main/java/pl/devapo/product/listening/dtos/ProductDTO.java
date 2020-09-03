package pl.devapo.product.listening.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devapo.product.listening.models.DeliveryOption;
import pl.devapo.product.listening.models.PaymentOption;
import pl.devapo.product.listening.models.Product;
import pl.devapo.product.listening.models.ProductCategory;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private ProductCategory productCategory;
    private String name;
    private String description;
    private String unitPrice;
    private Long inventory;
    private PaymentOption paymentOptions;
    private DeliveryOption deliveryOptions;

    public static ProductDTO of(Product product) {
        return new ProductDTO(
                product.getProductCategory(),
                product.getName(),
                product.getDescription(),
                product.getUnitPrice(),
                product.getInventory(),
                product.getPaymentOptions(),
                product.getDeliveryOptions()
        );
    }
}
