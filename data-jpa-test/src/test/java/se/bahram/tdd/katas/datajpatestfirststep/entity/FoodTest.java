package se.bahram.tdd.katas.datajpatestfirststep.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    void food_has_title_and_price() {
        Food food = new Food("Pizza", 104);
        assertEquals("Pizza", food.getTitle());
        assertEquals(104, food.getPrice());
    }

    @Test
    void food_price_should_not_be_less_than_0() {
        assertThrows(IllegalArgumentException.class, () -> new Food("Pizza", -100));
    }

    @Test
    void food_price_should_not_be_more_than_15000() {
        assertThrows(IllegalArgumentException.class, () -> new Food("Pizza", 15001));
    }
}
