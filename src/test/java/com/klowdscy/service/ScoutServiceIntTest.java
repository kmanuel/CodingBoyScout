package com.klowdscy.service;

import com.klowdscy.CodingBoyScoutApplication;
import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import com.klowdscy.exception.UnknownScoutException;
import com.klowdscy.service.ScoutService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Integration Test for ScoutService
 * Created by manuel on 27.11.17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CodingBoyScoutApplication.class})
@Transactional
public class ScoutServiceIntTest {

    @Autowired
    private ScoutService scoutService;

    @Autowired
    private ScoutDao scoutDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenExistingScout_whenAddPoints_thenPointsAdded()
            throws UnknownScoutException {
        // given
        Scout scout = new Scout("scout1", 1);
        scoutDao.saveAndFlush(scout);
        // when
        scoutService.addToScore(scout.getId(), 12);
        // then
        assertThat(
                scoutDao.findOne(scout.getId()).getPoints(),
                is(13L));
    }

    @Test
    public void givenNonExistingScout_whenAddPoints_thenUnknownScoutException()
            throws UnknownScoutException {
        expectedException.expect(UnknownScoutException.class);
        scoutService.addToScore(Long.MAX_VALUE, 13);
    }

    @Test
    public void givenValidScout_whenGetScoreFor_thenReturnsScore()
            throws UnknownScoutException {
        // given
        Scout scout = new Scout("scout1", 1);
        scoutDao.saveAndFlush(scout);
        // when then
        assertThat(
                scoutService.getScoreFor(scout.getId()),
                is(1L));
    }

    @Test
    public void givenInvalidScout_whenGetScoreFor_thenUnknownScoutException()
            throws UnknownScoutException {
        expectedException.expect(UnknownScoutException.class);
        scoutService.getScoreFor(34593);
    }

}
