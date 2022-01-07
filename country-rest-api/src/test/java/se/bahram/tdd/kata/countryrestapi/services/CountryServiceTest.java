package se.bahram.tdd.kata.countryrestapi.services;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.bahram.tdd.kata.countryrestapi.model.Country;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.Optional;

public class CountryServiceTest {

    private CountryService countryService;

    @BeforeEach
    void setup() {
        this.countryService = new CountryService();
    }

    @Test
    void when_find_by_id_it_should_return_empty() {
        // when
        Optional<Country> country = countryService.findById(1);
        // then
        assertThat(country).isEqualTo(Optional.empty());
    }

    @Test
    void given_save_country_when_find_by_1_it_should_return_country() {
        // given
        countryService.save("Sweden");
        // when
        Optional<Country> optionalCountry = countryService.findById(1);
        // then
        assertThat(optionalCountry.isPresent()).isEqualTo(true);
        assertThat(optionalCountry.get().getId()).isEqualTo(1);
        assertThat(optionalCountry.get().getTitle()).isEqualTo("Sweden");

    }

    @Test
    void when_find_all_it_should_return_empty_list() {
        // when
        List<Country> countries = countryService.findAll();
        // then
        assertThat(countries.size()).isEqualTo(0);
    }

    @Test
    void given_save_country_when_find_all_it_should_return_list_with_size_1() {
        CountryService countryService = new CountryService();
        // given
        countryService.save("Sweden");
        // when
        List<Country> countries = countryService.findAll();
        // then
        assertThat(countries.size()).isEqualTo(1);
    }

    @Test
    void when_delete_1_it_should_return_false() {
        // when
        boolean result = countryService.delete(1);
        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    void given_save_country_when_delete_1_it_should_return_false() {
        CountryService countryService = new CountryService();
        // given
        countryService.save("Sweden");
        // when
        boolean result = countryService.delete(1);
        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void when_save_sweden_it_should_return_1() {
        // when
        int swedenId = countryService.save("Sweden");
        // then
        assertThat(swedenId).isEqualTo(1);
    }

    @Test
    void when_save_B_it_should_return_IllegalArgumentException() {
        // when and then
        assertThrows(IllegalArgumentException.class, () -> countryService.save("B"));
    }

    @Test
    void when_update_1_by_sweden_it_should_return_true() {
        // given
        countryService.save("Sweden");
        // when and then
        assertThat(countryService.update(1, "Sweden")).isEqualTo(true);
    }

    @Test
    void when_update_1_by_B_it_should_return_IllegalArgumentException() {
        // given
        countryService.save("Sweden");
        // when and then
        assertThrows(IllegalArgumentException.class, () -> countryService.update(1, "B"));
    }

}
