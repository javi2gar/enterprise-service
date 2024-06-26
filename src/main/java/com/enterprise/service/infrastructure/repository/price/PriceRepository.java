package com.enterprise.service.infrastructure.repository.price;

import com.enterprise.service.infrastructure.entity.price.PriceEntity;
import com.enterprise.service.infrastructure.entity.price.PriceEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, PriceEntityPK> {

    @Query(value = "SELECT * FROM PRICES p " +
            "WHERE brand_id = :brandId " +
            "AND product_id = :productId " +
            "AND start_date <= :requestDate " +
            "AND end_date >= :requestDate", nativeQuery = true)
    List<PriceEntity> findAllByBrandIdAndProductIdAndDate(short brandId, Long productId, LocalDateTime requestDate);

}
