package ar.com.flexibility.examen.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE client SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Client extends GenericModel {
    private String fullName;
    private Long dni;
    private String email;
    @OneToMany(mappedBy = "client")
    private Set<Purchase> purchases;
}
