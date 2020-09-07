package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductDto;
import ar.com.flexibility.examen.app.api.PurchaseDto;
import ar.com.flexibility.examen.app.api.creationDto.ProductCreationDto;
import ar.com.flexibility.examen.app.api.creationDto.PurchaseCreationDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.impl.ProductService;
import ar.com.flexibility.examen.domain.service.impl.PurchaseService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("purchase")
@Getter
public class PurchaseController {

    @Autowired
    private PurchaseService service;
    private ModelMapper modelMapper;

    public PurchaseController(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> read() {
        return okResponse(getService().findAll());
    }

    @PostMapping
    public ResponseEntity<PurchaseDto> create(@RequestBody PurchaseCreationDto dto) {
        return okResponse(this.service.createFromDto(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDto> update(@PathVariable(name = "id") Long id, @RequestBody PurchaseDto dto) {
        return okResponse(getService().updateFromDTO(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> read(@PathVariable(name = "id") Long id) {
        return okResponse(getService().getById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        getService().deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<PurchaseDto>> okResponse(List<Purchase> src) {
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<PurchaseDto> okResponse(Purchase src) {
        return ResponseEntity.ok(this.toDTO(src));
    }


    private PurchaseDto toDTO(Purchase src) {
        return getModelMapper().map(src, PurchaseDto.class);
    }


}

