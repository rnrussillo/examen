package ar.com.flexibility.examen.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE seller SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Seller extends GenericModel {
    private String fullName;
    private Long dni;
    private String email;
    @OneToMany(mappedBy = "seller")
    private Set<Purchase> purchases;
}
