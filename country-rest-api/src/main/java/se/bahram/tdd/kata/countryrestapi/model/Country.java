package se.bahram.tdd.kata.countryrestapi.model;

import static org.apache.commons.lang3.Validate.*;

public class Country {

    private static final int TITLE_MIN_LENGTH = 3;
    private static final int TITLE_MAX_LENGTH = 64;
    public static final String TITLE_VALID_CHARACTERS = "[A-Za-z]+";

    public int id;
    public String title;

    public Country(int id, String title) throws IllegalArgumentException{
        this(title);
        this.id = id;
    }

    public Country(String title) throws IllegalArgumentException{
        this.setTitle(title);
    }

    public void setTitle(String title) throws IllegalArgumentException{
        notNull(title);
        notBlank(title);
        title = title.trim();
        inclusiveBetween(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, title.length());
        matchesPattern(title, TITLE_VALID_CHARACTERS);
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
