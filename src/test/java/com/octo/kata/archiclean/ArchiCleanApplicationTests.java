package com.octo.kata.archiclean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArchiCleanApplication.class)
public class ArchiCleanApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_get_glider() throws Exception {
        test_get("glider", BaseFixtures.GLIDER);
    }

    @Test
    public void test_get_lwss() throws Exception {
        test_get("lwss", BaseFixtures.LWSS);
    }

    @Test
    public void test_get_penta() throws Exception {
        test_get("penta", BaseFixtures.PENTA);
    }

    @Test
    public void test_get_pulsar() throws Exception {
        test_get("pulsar", BaseFixtures.PULSAR);
    }

    @Test
    public void test_post_glider() throws Exception {
        test_post(BaseFixtures.GLIDER, EvolvedFixtures.GLIDER);
    }

    @Test
    public void test_post_lwss() throws Exception {
        test_post(BaseFixtures.LWSS, EvolvedFixtures.LWSS);
    }

    @Test
    public void test_post_penta() throws Exception {
        test_post(BaseFixtures.PENTA, EvolvedFixtures.PENTA);
    }

    @Test
    public void test_post_pulsar() throws Exception {
        test_post(BaseFixtures.PULSAR, EvolvedFixtures.PULSAR);
    }

    private void test_get(String template, String base) throws Exception {
        // when
        ResultActions resultActions = mvc.perform(
                get("/grid")
                        .param("template", template)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(base));
    }

    private void test_post(String base, String evolved) throws Exception {
        // when
        ResultActions resultActions = mvc.perform(
                post("/grid")
                        .content(base)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(evolved));
    }
}
