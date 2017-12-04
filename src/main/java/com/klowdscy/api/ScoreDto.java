package com.klowdscy.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
class ScoreDto {
    @Min(1)
    private final long score;
}
