package se.bahram.tdd.katas.datajpatestfirststep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.bahram.tdd.katas.datajpatestfirststep.entity.Food;

import java.util.List;

@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @AfterEach
    void afterEach(){
        this.foodRepository.deleteAll();
    }

    @Test
    void given_save_food_when_find_all_then_list_size_should_be_one() {
        // given
        Food food = new Food("Pizza", 104);
        foodRepository.save(food);
        // when
        List<Food> foods = foodRepository.findAll();
        // then
        Assertions.assertEquals(1, foods.size());
    }

    @Test
    void given_save_food_when_find_all_then_first_food_id_should_not_be_null() {
        // given
        Food food = new Food("Pizza", 104);
        foodRepository.save(food);
        // when
        List<Food> foods = foodRepository.findAll();
        // then
        Food fistFood = foods.get(0);
        Assertions.assertNotNull(fistFood.getId());
    }

    @Test
    void given_save_food_when_delete_then_list_size_should_be_zero() {
        // given
        Food food = new Food("Pizza", 104);
        foodRepository.save(food);
        // when
        foodRepository.delete(food);
        // then
        Assertions.assertEquals(0, foodRepository.findAll().size());
    }
}
