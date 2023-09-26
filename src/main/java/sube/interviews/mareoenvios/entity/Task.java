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
@Table(name = "shipping_task")
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

    @Column(name = "start_date", nullable = true)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    public Task(){}

    public Task(Shipping shippingId, String state, LocalDateTime startDate) {
        this.shippingId = shippingId;
        this.state = state;
        this.startDate = startDate;
    }

    public Task(Shipping shippingId, String state, String error, LocalDateTime startDate, LocalDateTime endDate) {
        this.shippingId = shippingId;
        this.state = state;
        this.error = error;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
