package com.klowdscy;

import org.springframework.stereotype.Service;

/**
 * Created by manuel on 26.11.17.
 */
@Service
public class ScoutService {

    private final ScoutDao scoutDao;

    public ScoutService(ScoutDao scoutDao) {
        this.scoutDao = scoutDao;
    }

    public void addToScore(long scoutId, long additionalScore) throws UnknownScoutException {
        if (!scoutDao.exists(scoutId)) {
            throw new UnknownScoutException("no scout found for id=" + scoutId);
        }
        Scout scout = scoutDao.findOne(scoutId);
        scout.setPoints(scout.getPoints() + additionalScore);
        scoutDao.save(scout);
    }

    public long getScoreFor(long scoutId) throws UnknownScoutException {
        if (!scoutDao.exists(scoutId)) {
            throw new UnknownScoutException("no scout found for id=" + scoutId);
        }
        return scoutDao.findOne(scoutId).getPoints();
    }
}