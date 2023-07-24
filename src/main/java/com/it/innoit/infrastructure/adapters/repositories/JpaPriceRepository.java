package com.it.innoit.infrastructure.adapters.repositories;

import com.it.innoit.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND p.startDate <= :dateTime " +
            "AND p.endDate >= :dateTime " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findByBrandIdAndProductIdAndDateTime(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("dateTime") LocalDateTime dateTime);

}
