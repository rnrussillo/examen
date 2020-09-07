package ar.com.flexibility.examen.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE purchase SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Purchase extends GenericModel {
    private String name;
    private String description;
    private Float total;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "product_purchases", joinColumns = @JoinColumn(name = "purchases_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
}
