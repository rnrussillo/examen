package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ProductDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return new ArrayList<>(getRepository().findAll());
    }


    @Transactional
    public Product persist(Product product) {
        return getRepository().save(product);
    }

    @Transactional
    public Product updateFromDTO(Long id, ProductDto dto) {
        Product productToUpdate = this.getById(id);
        if (dto.getDescription() != null) {
            productToUpdate.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            productToUpdate.setPrice(dto.getPrice());
        }

        if (dto.getName() != null) {
            productToUpdate.setName(dto.getName());
        }
        return persist(productToUpdate);

    }

    @Transactional
    public void deleteById(Long id) {
        getRepository().delete(id);
    }

    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return getRepository().findOne(id);
    }

    public List<Product> findByIdIn(List<Long> productIds) {
        return this.repository.findByIdIn(productIds);
    }
}
