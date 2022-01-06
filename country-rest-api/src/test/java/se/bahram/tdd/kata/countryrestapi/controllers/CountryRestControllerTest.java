package se.bahram.tdd.kata.countryrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.bahram.tdd.kata.countryrestapi.model.Country;
import se.bahram.tdd.kata.countryrestapi.services.CountryService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void when_get_all_it_should_return_all_countries() throws Exception {
        mockMvc
                .perform(get("/countries/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void when_get_all_it_should_return_two_countries() throws Exception {
        when(countryService.findAll()).thenReturn(
                Arrays.asList(
                        new Country(1, "Sweden"),
                        new Country(2, "Iran")

                )
        );
        mockMvc
                .perform(get("/countries/"))
                .andExpect(content().json("[{id: 1, title: Sweden}, {id: 2, title: Iran}]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void when_get_1_it_should_return_country() throws Exception {
        when(countryService.findById(1)).thenReturn(Optional.of(new Country(1, "Sweden")));
        when(countryService.delete(1)).thenReturn(true);
        mockMvc
                .perform(get("/countries/1"))
                .andExpect(content().json("{id: 1, title: Sweden}"))
                .andExpect(status().isOk());
    }

    @Test
    void when_get_2_it_should_status_not_found() throws Exception {
        when(countryService.findById(2)).thenReturn(Optional.empty());
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

    @Test
    void when_delete_1_should_status_ok() throws Exception{
        when(countryService.findById(1)).thenReturn(Optional.of(new Country(1, "Sweden")));
        mockMvc
                .perform(delete("/countries/1"))
                .andExpect(status().isOk());
    }

    @Test
    void when_delete_2_should_status_not_found() throws Exception{
        when(countryService.findById(2)).thenReturn(Optional.empty());
        mockMvc
                .perform(delete("/countries/2"))
                .andExpect(status().isNotFound());
    }
}
