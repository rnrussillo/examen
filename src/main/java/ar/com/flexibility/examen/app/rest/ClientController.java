package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientDto;
import ar.com.flexibility.examen.app.api.creationDto.ClientCreationDto;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.impl.ClientService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("client")
@Getter
public class ClientController {

    @Autowired
    private ClientService service;
    private ModelMapper modelMapper;

    public ClientController(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> read() {
        return okResponse(getService().findAll());
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientCreationDto dto) {
        return okResponse(getService().persist(modelMapper.map(dto, Client.class)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable(name = "id") Long id, @RequestBody ClientDto dto) {
        return okResponse(getService().updateFromDTO(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> read(@PathVariable(name = "id") Long id) {
        return okResponse(getService().getById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        getService().deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<ClientDto>> okResponse(List<Client> src) {
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<ClientDto> okResponse(Client src) {
        return ResponseEntity.ok(this.toDTO(src));
    }


    private ClientDto toDTO(Client src) {
        return getModelMapper().map(src, ClientDto.class);
    }


}

