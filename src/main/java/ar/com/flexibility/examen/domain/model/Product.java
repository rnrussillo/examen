package ar.com.flexibility.examen.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;


@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE product SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Product extends GenericModel {
    private String name;
    private String description;
    private Float price;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Purchase> purchases;
}
