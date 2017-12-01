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

    @GetMapping("/{scoutName}/score")
    public long getScoutScore(@PathVariable String scoutName) throws UnknownScoutException {
        return this.scoutService.getScoreFor(scoutName);
    }

    @PostMapping("{scoutName}/score")
    public void addtoScore(@PathVariable String scoutName,
                           @Valid @RequestBody ScoreDto scoreDto) {
        this.scoutService.addToScore(scoutName, scoreDto.getScore());
    }
}
