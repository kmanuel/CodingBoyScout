package com.klowdscy.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klowdscy.api.ScoreDto;
import com.klowdscy.api.ScoutRestController;
import com.klowdscy.service.ScoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ScoutRestController Mvc Tests
 * Created by manuel on 26.11.17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ScoutRestController.class)
public class ScoutRestControllerTest {

    private static final String CONTROLLER_PREFIX = "/api/v1/scout/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ScoutService scoutService;

    @Test
    public void testIncreaseScoreByZero_returnsBadRequest() throws Exception {
        mockMvc.perform(post(CONTROLLER_PREFIX + "{scoutId}/score", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(scoreDtoJson(0)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testIncreaseScoreByOne_increasesScore() throws Exception {
        mockMvc.perform(
                post(CONTROLLER_PREFIX + "{scoutId}/score", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(scoreDtoJson(3)))
                .andExpect(status().isOk());

        verify(scoutService).addToScore(1L, 3);
    }

    private String scoreDtoJson(int score) throws JsonProcessingException {
        ScoreDto scoreDto = new ScoreDto(score);
        return objectMapper.writeValueAsString(scoreDto);
    }

}
