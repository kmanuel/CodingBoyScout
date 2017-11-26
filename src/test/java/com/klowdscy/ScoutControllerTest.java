package com.klowdscy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by manuel on 26.11.17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ScoutController.class)
public class ScoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ScoutDao scoutDao;

    @Test
    public void testListReturnsListView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"));
    }

    @Test
    public void testListReturnsAllScouts() throws Exception {
        List<Scout> testScouts = Arrays.asList(
                new Scout(1L, "scout1", 1L),
                new Scout(2L, "scout2", 2L));
        Mockito.when(scoutDao.findAll()).thenReturn(testScouts);

        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("scouts"))
                .andExpect(model().attribute("scouts", contains(testScouts.toArray())));
    }

    @Test
    public void testListReturnsEmptyOnEmptyList() throws Exception {
        Mockito.when(scoutDao.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("scouts"))
                .andExpect(model().attribute("scouts", empty()));
    }

}