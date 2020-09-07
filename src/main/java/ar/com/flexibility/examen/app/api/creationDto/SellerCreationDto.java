package ar.com.flexibility.examen.app.api.creationDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerCreationDto {
    private String fullName;
    private Long dni;
    private String email;
}
