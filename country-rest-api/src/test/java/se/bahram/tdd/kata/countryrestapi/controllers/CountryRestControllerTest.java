package se.bahram.tdd.kata.countryrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void when_get_all_it_should_return_all_countries() throws Exception {
        mockMvc
                .perform(get("/countries/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: 1, title: Sweden}]"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void when_get_1_it_should_return_country() throws Exception {
        mockMvc
                .perform(get("/countries/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1, title: Sweden}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));;
    }

    @Test
    void when_get_2_it_should_status_not_found() throws Exception {
        mockMvc
                .perform(get("/countries/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void when_get_aa_it_should_status_bad_request() throws Exception {
        mockMvc
                .perform(get("/countries/aa"))
                .andExpect(status().isBadRequest());
    }
}
