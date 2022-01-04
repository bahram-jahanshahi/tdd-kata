package se.bahram.tdd.kata.countryrestapi.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void title_should_be_trimmed() {
        Country country = new Country(" Sweden ");
        Assertions.assertThat(country.title).isEqualTo("Sweden");
    }

    @Test
    void when_title_length_is_smaller_3_then_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> new Country("bb"));
        assertThrows(IllegalArgumentException.class, () -> new Country("a"));
    }

    @Test
    void title_should_not_be_blank() {
        assertThrows(IllegalArgumentException.class, () -> new Country(""));
    }

    @Test
    void title_should_not_be_null() {
        assertThrows(NullPointerException.class, () -> new Country(null));
    }

    @Test
    void title_should_contains_only_a_to_z_characters() {
        assertThrows(IllegalArgumentException.class, () -> new Country("123"));
    }
}