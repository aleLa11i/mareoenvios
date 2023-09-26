package sube.interviews.mareoenvios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "shipping")
public class Shipping implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "state")
    private String state;

    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "arrive_date")
    private Date arriveDate;

    @Column(name = "priority")
    private Integer priority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shipping_item",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "shipping_id", referencedColumnName = "id", insertable = false, updatable = false)
    )
    private List<Product> products = new ArrayList<Product>();

    @OneToMany( orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn( name = "shipping_id", referencedColumnName = "id")
    private List<Task> tasks = new ArrayList<>();

}
