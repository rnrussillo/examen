package ar.com.flexibility.examen.app.api.creationDto;

import ar.com.flexibility.examen.app.api.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientCreationDto {
    private String fullName;
    private Long dni;
    private String email;
}
