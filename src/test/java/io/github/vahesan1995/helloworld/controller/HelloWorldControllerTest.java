package io.github.vahesan1995.helloworld.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenNameStartsWithAtoM_thenReturns200() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Hello Alice\"}"));
    }

    @Test
    void whenNameStartsWithNtoZ_thenReturns400() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nabeel"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    void whenNameMissing_thenReturns400() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    void whenNameEmpty_thenReturns400() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    void whenNameStartsWithNonLetter_thenReturns400() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "1abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    void whenSingleLetterM_thenReturns200() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "m"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Hello M\"}"));
    }

    @Test
    void whenSingleLetterN_thenReturns400() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "n"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }
}
