package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.PurchaseDto;
import ar.com.flexibility.examen.app.api.creationDto.PurchaseCreationDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Getter
public class PurchaseService {
    @Autowired
    private PurchaseRepository repository;
    @Autowired
    ProductService productService;
    @Autowired
    ClientService clientService;
    @Autowired
    SellerService sellerService;


    @Transactional(readOnly = true)
    public List<Purchase> findAll() {
        return new ArrayList<>(getRepository().findAll());
    }


    @Transactional
    public Purchase persist(Purchase purchase) {
        return getRepository().save(purchase);
    }

    @Transactional
    public Purchase updateFromDTO(Long id, PurchaseDto dto) {
        Purchase purchaseToUpdate = this.getById(id);
        if (dto.getDescription() != null) {
            purchaseToUpdate.setDescription(dto.getDescription());
        }

        if (dto.getName() != null) {
            purchaseToUpdate.setName(dto.getName());
        }

        if (dto.getStatus() != null) {
            purchaseToUpdate.setStatus(dto.getStatus());
        }
        return persist(purchaseToUpdate);

    }

    @Transactional
    public void deleteById(Long id) {
        getRepository().delete(id);
    }

    @Transactional(readOnly = true)
    public Purchase getById(Long id) {
        return getRepository().findOne(id);
    }

    public Purchase createFromDto(PurchaseCreationDto dto) {
        Purchase purchaseToPersist = new Purchase();
        purchaseToPersist.setSeller(this.sellerService.getById(dto.getSellerId()));
        purchaseToPersist.setClient(this.clientService.getById(dto.getClientId()));
        purchaseToPersist.setProducts(this.getProducts(dto.getProductIds()));
        purchaseToPersist.setName(dto.getName());
        purchaseToPersist.setDescription(dto.getDescription());
        purchaseToPersist.setStatus(PurchaseStatus.PENDING);
        purchaseToPersist.setTotal(calculateTotal(purchaseToPersist.getProducts()));
        return this.persist(purchaseToPersist);

    }

    private Float calculateTotal(Set<Product> products) {
        return (float) products.stream().mapToDouble(Product::getPrice).sum();
    }

    private Set<Product> getProducts(List<Long> productIds) {
        List<Product> products = this.productService.findByIdIn(productIds);
        return new HashSet<>(products);
    }
}
