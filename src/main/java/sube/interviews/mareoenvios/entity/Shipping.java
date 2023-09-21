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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shipping_item",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "shipping_id", referencedColumnName = "id", insertable = false, updatable = false)
    )
    private List<Product> products = new ArrayList<Product>();

    public Shipping() {
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getState() {
        return state;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public List<Product> getProducts() {
        return products;
    }
}
