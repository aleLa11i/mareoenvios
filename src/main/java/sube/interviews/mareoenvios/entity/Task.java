package sube.interviews.mareoenvios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

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

    @Column(name = "date", nullable = true)
    private LocalDateTime date;

    public Task(){}

    public Task(Shipping shippingId, String state) {
        this.shippingId = shippingId;
        this.state = state;
    }

    public Task(Shipping shippingId, String state, LocalDateTime date) {
        this(shippingId, state);
        this.date = date;
    }

    public Task(Shipping shippingId, String state, String error) {
        this(shippingId, state);
        this.error = error;
    }

    public Task(Shipping shippingId, String state, String error, LocalDateTime date) {
        this(shippingId, state, error);
        this.date = date;
    }
}
