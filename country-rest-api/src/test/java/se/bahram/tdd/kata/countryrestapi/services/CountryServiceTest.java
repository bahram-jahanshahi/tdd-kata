package se.bahram.tdd.kata.countryrestapi.services;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CountryServiceTest {


    @Test
    void when_find_by_id_it_should_return_empty() {
        CountryService countryService = new CountryService();
        assertThat(countryService.findById(1)).isEqualTo(Optional.empty());
    }

    @Test
    void when_find_all_it_should_return_empty_list() {
        CountryService countryService = new CountryService();
        assertThat(countryService.findAll().size()).isEqualTo(0);
    }

    @Test
    void when_delete_1_it_should_return_false() {
        CountryService countryService = new CountryService();
        assertThat(countryService.delete(1)).isEqualTo(false);
    }

    @Test
    void when_save_sweden_it_should_return_country() {
        CountryService countryService = new CountryService();
        assertThat(countryService.save("Sweden")).isEqualTo(1);
    }

    @Test
    void when_save_B_it_should_return_IllegalArgumentException() {
        CountryService countryService = new CountryService();
        assertThrows(IllegalArgumentException.class, () -> countryService.save("B"));
    }

}
