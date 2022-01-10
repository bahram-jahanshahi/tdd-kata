package se.bahram.tdd.katas.datajpatestfirststep.entity;

import org.apache.commons.lang3.Validate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private int price;

    public Food(String title, int price) {

        Validate.inclusiveBetween(0, 15000, price);

        this.title = title;
        this.price = price;
    }

    public Food() {
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return this.price;
    }

    public Object getId() {
        return this.id;
    }
}
