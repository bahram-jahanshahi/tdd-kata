package se.bahram.tdd.kata.countryrestapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bahram.tdd.kata.countryrestapi.commons.NumberUtils;
import se.bahram.tdd.kata.countryrestapi.model.Country;
import se.bahram.tdd.kata.countryrestapi.services.CountryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryRestController {

    final CountryService countryService;

    private List<Country> countries = Arrays.asList(
            new Country(1, "Sweden")
    );

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    private ResponseEntity<List<Country>> findAll() {

        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Country> findById(@PathVariable("id") String id) {

        if (!NumberUtils.isInteger(id)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Country> country = this.countryService.findById(Integer.parseInt(id));
        if (country.isPresent()) {
            return ResponseEntity.ok(country.get());
        }
        return ResponseEntity.notFound().build();
    }


}
