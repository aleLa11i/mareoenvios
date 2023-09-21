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

    // Se generan Getters ya que mapstruct es incompatible con Lombok

    public String getDescription() {
        return description;
    }

    public Float getWeight() {
        return weight;
    }
}
