package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ClientDto;
import ar.com.flexibility.examen.app.api.SellerDto;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class SellerService {
    @Autowired
    private SellerRepository repository;


    @Transactional(readOnly = true)
    public List<Seller> findAll() {
        return new ArrayList<>(getRepository().findAll());
    }


    @Transactional
    public Seller persist(Seller seller) {
        return getRepository().save(seller);
    }

    @Transactional
    public Seller updateFromDTO(Long id, SellerDto dto) {
        Seller sellerToUpdate = this.getById(id);
        if (dto.getDni() != null) {
            sellerToUpdate.setDni(dto.getDni());
        }

        if (dto.getEmail() != null) {
            sellerToUpdate.setEmail(dto.getEmail());
        }

        if (dto.getFullName() != null) {
            sellerToUpdate.setFullName(dto.getFullName());
        }

        return persist(sellerToUpdate);

    }

    @Transactional
    public void deleteById(Long id) {
        getRepository().delete(id);
    }

    @Transactional(readOnly = true)
    public Seller getById(Long id) {
        return getRepository().findOne(id);
    }

}
