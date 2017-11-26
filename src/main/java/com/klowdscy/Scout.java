package com.klowdscy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by manuel on 26.11.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Scout {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long points;
}
