package se.bahram.tdd.kata.countryrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
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
    void when_delete_1_should_status_no_content_204() throws Exception {
        when(countryService.findById(1)).thenReturn(Optional.of(new Country(1, "Sweden")));
        when(countryService.delete(1)).thenReturn(true);
        mockMvc
                .perform(delete("/countries/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void when_delete_2_should_status_not_found() throws Exception {
        when(countryService.findById(2)).thenReturn(Optional.empty());
        when(countryService.delete(2)).thenReturn(false);
        mockMvc
                .perform(delete("/countries/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void when_delete_3_should_status_not_modified_304() throws Exception {
        when(countryService.findById(3)).thenReturn(Optional.of(new Country(3, "Finland")));
        when(countryService.delete(3)).thenReturn(false);
        mockMvc
                .perform(delete("/countries/3"))
                .andExpect(status().isNotModified());
    }

    @Test
    void when_save_sweden_it_should_return_4_and_status_created() throws Exception {
        when(countryService.save("Sweden")).thenReturn(4);
        mockMvc
                .perform(post("/countries").content("Sweden"))
                .andExpect(header().string(HttpHeaders.LOCATION, "/countries/4"))
                .andExpect(status().isCreated());
    }

    @Test
    void when_save_something_wrong_it_should_return_bad_request() throws Exception {
        when(countryService.save("SOMETHING_WRONG")).thenThrow(IllegalArgumentException.class);
        mockMvc
                .perform(post("/countries").content("SOMETHING_WRONG"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_update_sweden_it_should_return_status_accepted() throws Exception {
        // given
        when(countryService.update(1, "Sweden")).thenReturn(true);
        // when
        mockMvc
                .perform(put("/countries/1").content("Sweden"))
                .andExpect(status().isAccepted());

    }

    @Test
    void when_update_something_wrong_it_should_return_status_bad_request() throws Exception {
        // given
        when(countryService.update(1, "SOMETHING_WRONG")).thenReturn(false);
        // when
        mockMvc
                .perform(put("/countries/1").content("SOMETHING_WRONG"))
                .andExpect(status().isBadRequest());
    }


}
