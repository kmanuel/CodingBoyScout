package com.klowdscy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by manuel on 26.11.17.
 */
@Repository
public interface ScoutDao extends JpaRepository<Scout, Long> {
}
