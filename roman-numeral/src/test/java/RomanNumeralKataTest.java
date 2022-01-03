import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RomanNumeralKataTest {
    /*
     * The goal is to convert decimal number (1 - 10) to roman shape of that number
     * 1 -> I, 2 -> II, 3 -> III, 4 -> IV, 5 -> V, 6 -> VI, 7 -> VII, 8 -> VIII, 9 -> IX, 10 -> X
     */

    @Test
    void convertToRoman() {
        assertThat(RomanNumeralKata.convertTo(1)).isEqualTo("I");
        assertThat(RomanNumeralKata.convertTo(2)).isEqualTo("II");
        assertThat(RomanNumeralKata.convertTo(3)).isEqualTo("III");
        assertThat(RomanNumeralKata.convertTo(4)).isEqualTo("IV");
        assertThat(RomanNumeralKata.convertTo(5)).isEqualTo("V");
        assertThat(RomanNumeralKata.convertTo(6)).isEqualTo("VI");
        assertThat(RomanNumeralKata.convertTo(7)).isEqualTo("VII");
        assertThat(RomanNumeralKata.convertTo(8)).isEqualTo("VIII");
        assertThat(RomanNumeralKata.convertTo(9)).isEqualTo("IX");
        assertThat(RomanNumeralKata.convertTo(10)).isEqualTo("X");

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> RomanNumeralKata.convertTo(11));
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Decimal has to be between 1 to 10");
    }
}