package com.klowdscy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        when(scoutDao.exists(1L)).thenReturn(true);
        when(scoutDao.findOne(1L)).thenReturn(testScout);

        scoutService.addToScore(1L, 2);

        verify(scoutDao).findOne(1L);
        testScout.setPoints(3);
        verify(scoutDao).save(testScout);
    }

}
