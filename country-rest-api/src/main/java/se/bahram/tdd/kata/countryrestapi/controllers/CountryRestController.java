package se.bahram.tdd.kata.countryrestapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bahram.tdd.kata.countryrestapi.commons.NumberUtils;
import se.bahram.tdd.kata.countryrestapi.model.Country;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryRestController {

    private List<Country> countries = Arrays.asList(
            new Country(1, "Sweden")
    );

    @GetMapping
    private ResponseEntity<List<Country>> findAll() {

        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Country> findById(@PathVariable("id") String id) {

        if (!NumberUtils.isInteger(id)) {
            return ResponseEntity.badRequest().build();
        }

        for (Country country : countries) {
            if (country.id == Integer.parseInt(id)) {
                return ResponseEntity.ok(country);
            }
        }
        return ResponseEntity.notFound().build();
    }



}
