package com.klowdscy.service;

import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import com.klowdscy.exception.UnknownScoutException;
import com.klowdscy.service.ScoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by manuel on 26.11.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScoutServiceTest {

    @InjectMocks
    ScoutService scoutService;

    @Mock
    ScoutDao scoutDao;

    @Test
    public void testIncreaseScoreIncreasesScore() throws UnknownScoutException {
        Scout testScout = new Scout(1L, "scout1", 1);
        when(scoutDao.findOneByName("scout1")).thenReturn(Optional.of(testScout));

        scoutService.addToScore("scout1", 2);

        verify(scoutDao).findOneByName("scout1");
        testScout.setPoints(3);
        verify(scoutDao).save(testScout);
    }

    @Test
    public void testCreatesNewScoutWhenNoneFound() {
        when(scoutDao.findOneByName("scout1")).thenReturn(Optional.empty());

        scoutService.addToScore("scout1", 3);
        verify(scoutDao).save(any(Scout.class));
    }

}
