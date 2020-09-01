package com.dpslink.mobileapi.repository;

import com.dpslink.mobileapi.domain.DealerStop;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DealerStop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealerStopRepository extends JpaRepository<DealerStop, Long> {

//    @Query("FROM DealerStop w where w.delivery.id = :deliveryId")
//    List<DealerStop> findByCurrentDelivery(Long delivery_id);

    @Query("FROM DealerStop g where g.delivery.id = :deliveryId")
    List<DealerStop> findByCurrentDelivery(@Param("deliveryId") Long deliveryId);

    @Query("From DealerStop g where g.customerNumber1 = :customerNumber1 and g.customerNumber2 = :customerNumber2")
    Optional<DealerStop> findByCustomerNumber(@Param("customerNumber1") Integer customerNumber1, @Param("customerNumber2") Integer customerNumber2);

    Optional<DealerStop> findBySalesRepCode(String salesRepCode);
}
