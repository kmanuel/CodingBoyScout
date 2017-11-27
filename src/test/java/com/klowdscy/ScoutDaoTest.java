package com.klowdscy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ScoutDao Integration Tests
 * Created by manuel on 27.11.17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ScoutDaoTest {

    @Autowired
    private ScoutDao scoutDao;
    private Scout scout2;

    @Before
    public void saveTestUsers() {
        scoutDao.save(new Scout("scout1", 1));
        scout2 = scoutDao.save(new Scout("scout2", 2));
        scoutDao.save(new Scout("scout3", 3));
    }

    @Test
    public void givenNonExistingScoutId_whenFindOne_thenNone() {
        assertThat(
                scoutDao.findOne(Long.MAX_VALUE),
                is(nullValue()));
    }

    @Test
    public void givenExistingScoutId_whenFindOne_thenFound() {
        assertThat(
                scoutDao.findOne(scout2.getId()),
                is(scout2));
    }

    @Test
    public void whenCount_thenCountMatchesDb() {
        assertThat(
                scoutDao.count(),
                is(3L));
    }

}