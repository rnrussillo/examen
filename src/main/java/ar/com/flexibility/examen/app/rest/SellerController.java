package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerDto;
import ar.com.flexibility.examen.app.api.creationDto.SellerCreationDto;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.impl.SellerService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("seller")
@Getter
public class SellerController {

    @Autowired
    private SellerService service;
    private ModelMapper modelMapper;

    public SellerController(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<SellerDto>> read() {
        return okResponse(getService().findAll());
    }

    @PostMapping
    public ResponseEntity<SellerDto> create(@RequestBody SellerCreationDto dto) {
        return okResponse(getService().persist(modelMapper.map(dto, Seller.class)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SellerDto> update(@PathVariable(name = "id") Long id, @RequestBody SellerDto dto) {
        return okResponse(getService().updateFromDTO(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> read(@PathVariable(name = "id") Long id) {
        return okResponse(getService().getById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        getService().deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<SellerDto>> okResponse(List<Seller> src) {
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<SellerDto> okResponse(Seller src) {
        return ResponseEntity.ok(this.toDTO(src));
    }


    private SellerDto toDTO(Seller src) {
        return getModelMapper().map(src, SellerDto.class);
    }


}

