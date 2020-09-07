package ar.com.flexibility.examen.app.api.creationDto;

import ar.com.flexibility.examen.app.api.PurchaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCreationDto {
    private String name;
    private String description;
    private Float price;
}
