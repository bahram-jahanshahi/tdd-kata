package se.bahram.tdd.kata.countryrestapi.services;

import org.springframework.stereotype.Service;
import se.bahram.tdd.kata.countryrestapi.model.Country;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    public Optional<Country> findById(int i) {
        return Optional.empty();
    }

    public List<Country> findAll() {
        return Arrays.asList();
    }

    public boolean delete(int i) {
        return false;
    }
}
