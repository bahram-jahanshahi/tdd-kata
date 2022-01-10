package se.bahram.tdd.katas.datajpatestfirststep;

import org.springframework.data.repository.CrudRepository;
import se.bahram.tdd.katas.datajpatestfirststep.entity.Food;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, String> {

    List<Food> findAll();
}
