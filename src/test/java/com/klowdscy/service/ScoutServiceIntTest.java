package com.klowdscy.service;

import com.klowdscy.CodingBoyScoutApplication;
import com.klowdscy.testcategories.IntegrationTest;
import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import com.klowdscy.exception.UnknownScoutException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CodingBoyScoutApplication.class})
@Transactional
@Category(IntegrationTest.class)
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
        scoutService.addToScore(scout.getName(), 12);
        // then
        assertThat(
                scoutDao.findOne(scout.getId()).getPoints(),
                is(13L));
    }

    @Test
    public void givenNonExistingScout_whenAddPoints_thenScoutCreated()
            throws UnknownScoutException {
        // when
        scoutService.addToScore("newScout", 13);
        // then
        Optional<Scout> createdScout = scoutDao.findOneByName("newScout");
        assertThat(
                createdScout.isPresent(),
                is(true));
        assertThat(
                createdScout
                        .map(Scout::getName)
                        .orElse(""),
                is("newScout"));
        assertThat(
                createdScout
                        .map(Scout::getPoints)
                        .orElse(-1L),
                is(13L));
    }

    @Test
    public void givenValidScout_whenGetScoreFor_thenReturnsScore()
            throws UnknownScoutException {
        // given
        Scout scout = new Scout("scout1", 1);
        scoutDao.saveAndFlush(scout);
        // when then
        assertThat(
                scoutService.getScoreFor(scout.getName()),
                is(1L));
    }

    @Test
    public void givenInvalidScout_whenGetScoreFor_thenUnknownScoutException()
            throws UnknownScoutException {
        expectedException.expect(UnknownScoutException.class);
        scoutService.getScoreFor("randomName");
    }

}
