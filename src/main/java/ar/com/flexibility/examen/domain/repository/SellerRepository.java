package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query(value = "SELECT 1", nativeQuery = true)
    Integer testConnection();
}

