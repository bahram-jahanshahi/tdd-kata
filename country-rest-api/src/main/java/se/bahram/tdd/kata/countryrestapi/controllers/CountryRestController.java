package se.bahram.tdd.kata.countryrestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bahram.tdd.kata.countryrestapi.commons.NumberUtils;
import se.bahram.tdd.kata.countryrestapi.model.Country;
import se.bahram.tdd.kata.countryrestapi.services.CountryService;

import java.net.URI;
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

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable("id") String id) {
        Optional<Country> optionalCountry = countryService.findById(Integer.parseInt(id));
        if (optionalCountry.isPresent()) {
            boolean isDeleted = countryService.delete(Integer.parseInt(id));
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{title}")
    private ResponseEntity save(@PathVariable("title") String title) {
        try {
            int newId = countryService.save(title);
            return ResponseEntity.created(URI.create("/countries/" + newId)).build();
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.badRequest().build();
        }
    }

}
