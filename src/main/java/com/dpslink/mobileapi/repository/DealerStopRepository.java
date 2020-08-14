package com.dpslink.mobileapi.repository;

import com.dpslink.mobileapi.domain.DealerStop;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealerStop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealerStopRepository extends JpaRepository<DealerStop, Long> {
}
