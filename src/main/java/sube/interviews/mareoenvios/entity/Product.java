package sube.interviews.mareoenvios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private Float weight;

    @OneToMany( orphanRemoval = true)
    @JoinColumn( name = "product_id", referencedColumnName = "id")
    private List<ShippingItem> shippingItems = new ArrayList<>();

}
