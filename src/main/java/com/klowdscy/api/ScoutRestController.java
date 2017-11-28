package com.klowdscy.api;

import com.klowdscy.service.ScoutService;
import com.klowdscy.exception.UnknownScoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST Controller for Scout handling
 * Created by manuel on 26.11.17.
 */
@RestController
@RequestMapping("/api/v1/scout")
public class ScoutRestController {

    private final ScoutService scoutService;

    @Autowired
    public ScoutRestController(ScoutService scoutService) {
        this.scoutService = scoutService;
    }

    @GetMapping("/{scoutId}/score")
    public long getScoutScore(@PathVariable long scoutId) throws UnknownScoutException {
        return this.scoutService.getScoreFor(scoutId);
    }

    @PostMapping("{scoutId}/score")
    public void addToScore(@PathVariable long scoutId,
                           @Valid @RequestBody ScoreDto scoreDto) throws UnknownScoutException {
        this.scoutService.addToScore(scoutId, scoreDto.getScore());
    }
}
