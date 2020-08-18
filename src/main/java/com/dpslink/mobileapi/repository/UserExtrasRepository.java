package com.dpslink.mobileapi.repository;

import com.dpslink.mobileapi.domain.UserExtras;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the UserExtras entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtrasRepository extends JpaRepository<UserExtras, Long> {

    @Query("select userExtras from UserExtras userExtras where userExtras.user.login = ?#{principal.username}")
    List<UserExtras> findByUserIsCurrentUser();
}
