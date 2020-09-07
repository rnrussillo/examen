package ar.com.flexibility.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class GenericModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  protected Long id;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  protected Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  protected Date updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  protected Date deletedAt;
}
