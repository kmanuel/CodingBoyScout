package com.klowdscy.controller;

import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


/**
 * Selenium Test for Displaying of Scout Lists
 * Created by manuel on 28.11.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ScoutControllerSeleniumTest {

    @LocalServerPort
    private int port;
    private WebDriver driver;

    private String base;

    @MockBean
    private ScoutDao scoutDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void startDriver() throws Exception {
        driver = new ChromeDriver();
        this.base = "http://localhost:" + port;
    }

    @After
    public void closeDriver() {
        driver.close();
    }

    @Test
    public void testTitle() throws Exception {
        openListPage();
        assertThat(
                driver.getTitle(),
                is("Scouts"));
    }

    @Test
    public void testEmptyScoutListNoneAvailableMessageVisible() throws Exception {
        when(scoutDao.findAll())
                .thenReturn(
                        Collections.emptyList());

        openListPage();
        assertThat(
                driver.findElement(
                        By.cssSelector("#noneAvailableMessage"))
                        .getText(),
                is("There are currently no Scouts. Let's fix some warnings!"));
    }

    @Test
    public void testNonEmptyScoutList_NotNoneMessage() throws Exception {
        when(scoutDao.findAll())
                .thenReturn(
                        Collections.singletonList(
                                new Scout("scout", 1)));

        openListPage();

        expectedException.expect(NoSuchElementException.class);
        driver.findElement(
                By.cssSelector("#noneAvailableMessage")).getText();
    }

    @Test
    public void givenTwoScoutsInDb_whenList_thenTwoListItems() {
        when(scoutDao.findAll())
                .thenReturn(
                        Arrays.asList(
                                new Scout("scout1", 1),
                                new Scout("scout2", 2)));

        openListPage();

        List<WebElement> scoutListItems = driver.findElements(By.cssSelector("#scoutList li"));
        List<String> scoutListItemTexts = scoutListItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(scoutListItemTexts,
                IsCollectionContaining.hasItems("scout1 - 1", "scout2 - 2"));
    }

    private void openListPage() {
        driver.get(base + "/");
    }
}
