package ar.com.flexibility.examen.app.api.creationDto;

import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseCreationDto {
    private String name;
    private String description;
    private List<Long> productIds;
    private Long clientId;
    private Long sellerId;
    private PurchaseStatus status;
}
