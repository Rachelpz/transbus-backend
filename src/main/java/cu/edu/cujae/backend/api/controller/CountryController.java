package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.CountryDto;
import cu.edu.cujae.backend.core.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public ResponseEntity<List<CountryDto>> getCountries() throws SQLException {
        List<CountryDto> countryList = countryService.listCountries();
        return ResponseEntity.ok(countryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Integer id) throws SQLException {
        CountryDto country = countryService.getCountryById(id);
        return ResponseEntity.ok(country);
    }
}
