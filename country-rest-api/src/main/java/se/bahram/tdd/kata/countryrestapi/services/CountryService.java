package se.bahram.tdd.kata.countryrestapi.services;

import org.springframework.stereotype.Service;
import se.bahram.tdd.kata.countryrestapi.model.Country;

import java.util.*;

@Service
public class CountryService {

    private Map<Integer, Country> records = new HashMap<>();
    private Integer lastKey = 0;

    public Optional<Country> findById(int id) {
        if (records.containsKey(id)) {
            return Optional.of(records.get(id));
        }
        return Optional.empty();
    }

    public List<Country> findAll() {
        return records.values().stream().toList();
    }

    public boolean delete(int id) {
        if (records.containsKey(id)) {
            records.remove(id);
            return true;
        }
        return false;
    }

    public int save(String title){
        records.put(lastKey + 1, new Country(lastKey + 1, title));
        lastKey++;
        return lastKey;
    }

    public boolean update(int i, String title) {
        Optional<Country> optionalCountry = this.findById(i);
        if (optionalCountry.isPresent()) {
            optionalCountry.get().setTitle(title);
            return true;
        }
        return false;
    }
}
