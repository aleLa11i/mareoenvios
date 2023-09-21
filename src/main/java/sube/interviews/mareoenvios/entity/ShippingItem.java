package sube.interviews.mareoenvios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "shippinh_item")
public class ShippingItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_id")
    private Long shippingId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_count")
    private Long productCount;
}
