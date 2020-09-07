package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductDto;
import ar.com.flexibility.examen.app.api.creationDto.ProductCreationDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
@Getter
public class ProductController {

    @Autowired
    private ProductService service;
    private ModelMapper modelMapper;

    public ProductController(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> read() {
        return okResponse(getService().findAll());
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductCreationDto dto) {
        return okResponse(getService().persist(modelMapper.map(dto, Product.class)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable(name = "id") Long id, @RequestBody ProductDto dto) {
        return okResponse(getService().updateFromDTO(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> read(@PathVariable(name = "id") Long id) {
        return okResponse(getService().getById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        getService().deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<ProductDto>> okResponse(List<Product> src) {
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<ProductDto> okResponse(Product src) {
        return ResponseEntity.ok(this.toDTO(src));
    }


    private ProductDto toDTO(Product src) {
        return getModelMapper().map(src, ProductDto.class);
    }


}

