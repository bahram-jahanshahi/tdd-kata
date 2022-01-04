package se.bahram.tdd.kata.countryrestapi.model;

import static org.apache.commons.lang3.Validate.*;

public class Country {

    private static final int TITLE_MIN_LENGTH = 3;
    private static final int TITLE_MAX_LENGTH = 64;
    public static final String TITLE_VALID_CHARACTERS = "[A-Za-z]+";

    public int id;
    public String title;

    public Country(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Country(String title) {
        notNull(title);
        notBlank(title);
        title = title.trim();
        inclusiveBetween(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, title.length());
        matchesPattern(title, TITLE_VALID_CHARACTERS);
        this.title = title;
    }
}
