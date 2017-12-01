package com.klowdscy.service;

import com.klowdscy.exception.UnknownScoutException;
import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling of Scouts
 * Created by manuel on 26.11.17.
 */
@Service
public class ScoutService {

    private final ScoutDao scoutDao;

    @Autowired
    public ScoutService(ScoutDao scoutDao) {
        this.scoutDao = scoutDao;
    }

    public void addToScore(String scoutName, long additionalScore) throws UnknownScoutException {
        Scout scout = scoutDao.findOneByName(scoutName)
                .orElse(new Scout(scoutName, 0));
        scout.setPoints(scout.getPoints() + additionalScore);
        scoutDao.save(scout);
    }

    public long getScoreFor(String scoutName) {
        return scoutDao.findOneByName(scoutName)
                .map(Scout::getPoints)
                .orElseThrow(() -> new UnknownScoutException(scoutName));
    }
}