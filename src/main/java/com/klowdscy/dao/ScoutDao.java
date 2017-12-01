package com.klowdscy.dao;

import com.klowdscy.domain.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Dao for Scouts
 * Created by manuel on 26.11.17.
 */
@Repository
public interface ScoutDao extends JpaRepository<Scout, Long> {
    Optional<Scout> findOneByName(String scoutName);
}
