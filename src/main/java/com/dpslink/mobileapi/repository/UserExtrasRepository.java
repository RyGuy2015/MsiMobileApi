package com.dpslink.mobileapi.repository;

import com.dpslink.mobileapi.domain.UserExtras;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserExtras entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtrasRepository extends JpaRepository<UserExtras, Long> {
}
