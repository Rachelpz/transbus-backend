package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.GroupDto;
import cu.edu.cujae.backend.core.dto.CountryDto;
import cu.edu.cujae.backend.core.dto.RoleDto;
import cu.edu.cujae.backend.core.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CountryDto> listCountries() throws SQLException {
        List<CountryDto> countryList = new ArrayList<CountryDto>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM country");

            while (rs.next()) {
                CountryDto country = new CountryDto(rs.getInt("country_id")
                        , rs.getString("country_name"));
                countryList.add(country);
            }
        }
        return countryList;
    }

    @Override
    public CountryDto getCountryById(Integer CountryId) throws SQLException {
        CountryDto country = null;

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM country where country_id = ?");


            pstmt.setInt(1, CountryId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                country = new CountryDto(rs.getInt("country_id")
                        , rs.getString("country_name"));
            }
        }
        return country;
    }
}
