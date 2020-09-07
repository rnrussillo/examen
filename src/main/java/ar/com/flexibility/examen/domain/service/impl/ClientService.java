package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ClientDto;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class ClientService {
    @Autowired
    private ClientRepository repository;


    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return new ArrayList<>(getRepository().findAll());
    }


    @Transactional
    public Client persist(Client client) {
        return getRepository().save(client);
    }

    @Transactional
    public Client updateFromDTO(Long id, ClientDto dto) {
        Client clientToUpdate = this.getById(id);
        if (dto.getDni() != null) {
            clientToUpdate.setDni(dto.getDni());
        }

        if (dto.getEmail() != null) {
            clientToUpdate.setEmail(dto.getEmail());
        }

        if (dto.getFullName() != null) {
            clientToUpdate.setFullName(dto.getFullName());
        }

        return persist(clientToUpdate);

    }

    @Transactional
    public void deleteById(Long id) {
        getRepository().delete(id);
    }

    @Transactional(readOnly = true)
    public Client getById(Long id) {
        return getRepository().findOne(id);
    }

}
