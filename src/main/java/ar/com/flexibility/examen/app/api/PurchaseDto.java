package ar.com.flexibility.examen.app.api;

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
public class PurchaseDto {
    private Long id;
    private String name;
    private String description;
    private Float total;
    private PurchaseStatus status;

    private List<ProductDto> products;


    private ClientDto client;

    private SellerDto seller;
}
