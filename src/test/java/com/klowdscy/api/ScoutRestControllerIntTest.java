package com.klowdscy.api;

import com.klowdscy.CodingBoyScoutApplication;
import com.klowdscy.testcategories.IntegrationTest;
import com.klowdscy.dao.ScoutDao;
import com.klowdscy.domain.Scout;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Test for ScoutRestController
 * Created by manuel on 27.11.17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CodingBoyScoutApplication.class})
@WebAppConfiguration
@Transactional
@Category(IntegrationTest.class)
public class ScoutRestControllerIntTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ScoutDao scoutDao;

    private MockMvc mockMvc;
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        mappingJackson2HttpMessageConverter =
                (MappingJackson2HttpMessageConverter) Arrays.stream(converters)
                        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                        .findAny()
                        .orElse(null);

        assertThat(
                mappingJackson2HttpMessageConverter,
                is(notNullValue()));
    }

    @Test
    public void testContextLoads() {
        assertThat(
                wac,
                is(notNullValue()));
    }

    @Test
    public void givenUnknownScout_whenAddToScore_thenBadRequest() throws Exception {
        mockMvc.perform(
                get("/api/v1/scout/{scoutName}/score", 9999L))
                .andExpect(
                        status().isBadRequest());
    }

    @Test
    public void givenScoutName_whenGetScore_thenScoreReturned() throws Exception {
        Scout scout = scoutDao.save(new Scout("scout", 1337));

        mockMvc.perform(
                get("/api/v1/scout/{scoutName}/score", scout.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1337)));
    }

    @Test
    public void givenUnknownScoutName_whenGetScore_thenNotFound() throws Exception {
        mockMvc.perform(
                get("/api/v1/scout/{scoutName}/score", "someName"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenValidScoreUpdateAndScout_whenAddToScore_thenScoreUpdated() throws Exception {
        String scoutName = scoutDao.save(new Scout("scout", 7)).getName();

        mockMvc.perform(
                post("/api/v1/scout/{scoutName}/score", scoutName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(new ScoreDto(3))))
                .andExpect(
                        status().isOk());

        mockMvc.perform(
                get("/api/v1/scout/{scoutName}/score", scoutName))
                .andExpect(
                        status().isOk())
                .andExpect(
                        jsonPath("$",
                                is(10)));
    }

    @Test
    public void givenZeroScore_whenAddToScore_thenBadRequest() throws Exception {
        String scoutName = scoutDao.save(new Scout("scout", 7)).getName();

        mockMvc.perform(
                post("/api/v1/scout/{scoutName}/score", scoutName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(new ScoreDto(0))))
                .andExpect(
                        status().isBadRequest());
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter
                .write(o,
                        MediaType.APPLICATION_JSON,
                        mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
