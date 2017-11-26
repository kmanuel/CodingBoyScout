package com.klowdscy;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by manuel on 26.11.17.
 */
@Data
@Entity
public class Scout {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long points;
}
