package com.dpslink.mobileapi.repository;

import com.dpslink.mobileapi.domain.Delivery;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Delivery entity.
 */

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
