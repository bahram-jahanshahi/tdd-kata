public class RomanNumeralKata {


    public static String convertTo(int decimal) {

        if (decimal > 10 || decimal < 1) {
            throw new IllegalArgumentException("Decimal has to be between 1 to 10");
        }

        StringBuilder roman = new StringBuilder("");

        for (Roman topToBottomRoman: Roman.values()) {
            while (decimal >= topToBottomRoman.value) {
                roman.append(topToBottomRoman.title);
                decimal -= topToBottomRoman.value;
            }
        }
        return roman.toString();
    }

    enum Roman {
        TEN("X", 10),
        NINE("IX", 9),
        FIVE("V", 5),
        FOUR("IV", 4),
        ONE("I", 1);

        String title;
        int value;

        Roman(String title, int value) {
            this.title = title;
            this.value = value;
        }
    }
}
