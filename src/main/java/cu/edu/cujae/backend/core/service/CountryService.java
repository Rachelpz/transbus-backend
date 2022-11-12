package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.CountryDto;
import cu.edu.cujae.backend.core.dto.RoleDto;

import java.sql.SQLException;
import java.util.List;

public interface CountryService {
    List<CountryDto> listCountries() throws SQLException;
    CountryDto getCountryById(Integer CountryId) throws SQLException;
}
