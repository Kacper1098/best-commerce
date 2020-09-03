package pl.devapo.product.listening.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    private String name;
    private String description;
    private String unitPrice;
    private Long inventory;
    @Enumerated(EnumType.STRING)
    private PaymentOption paymentOptions;
    @Enumerated(EnumType.STRING)
    private DeliveryOption deliveryOptions;
}
