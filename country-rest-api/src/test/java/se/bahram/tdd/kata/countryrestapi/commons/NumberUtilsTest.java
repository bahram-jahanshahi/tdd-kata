package se.bahram.tdd.kata.countryrestapi.commons;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @Test
    void when_string_is_null_return_false() {
        boolean isInteger = NumberUtils.isInteger(null);
        assertThat(isInteger).isEqualTo(false);
    }

    @Test
    void when_string_is_aa_return_false() {
        boolean isInteger = NumberUtils.isInteger("aa");
        assertThat(isInteger).isEqualTo(false);
    }

    @Test
    void when_string_is_1_return_true() {
        boolean isInteger = NumberUtils.isInteger("1");
        assertThat(isInteger).isEqualTo(true);
    }

}