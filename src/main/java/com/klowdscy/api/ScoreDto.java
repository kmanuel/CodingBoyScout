package com.klowdscy.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Dto class for scores of Scouts
 * Created by manuel on 26.11.17.
 */
@Data
@AllArgsConstructor
class ScoreDto {
    @Min(1)
    private final long score;
}
