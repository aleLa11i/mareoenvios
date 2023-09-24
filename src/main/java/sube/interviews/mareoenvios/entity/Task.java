package sube.interviews.mareoenvios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "task")
public class Task {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "shipping_id")
    private Shipping shippingId;

    @Column(name = "state")
    private String state;

    @Column(name = "error", nullable = true)
    private String error;

    public Task(){}

    public Task(Shipping shippingId, String state) {
        this.shippingId = shippingId;
        this.state = state;
    }

    public Task(Shipping shippingId, String state, String error) {
        this.shippingId = shippingId;
        this.state = state;
        this.error = error;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShippingId(Shipping shippingId) {
        this.shippingId = shippingId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setError(String error) {
        this.error = error;
    }
}
