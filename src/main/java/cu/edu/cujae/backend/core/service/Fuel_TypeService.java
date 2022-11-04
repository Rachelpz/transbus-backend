package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.FuelDto;
import cu.edu.cujae.backend.core.dto.RoleDto;

import java.sql.SQLException;
import java.util.List;

public interface Fuel_TypeService {
    List<FuelDto> listFuels() throws SQLException;
    FuelDto getFuelById(Integer FuelId) throws SQLException;
    void createFuel(FuelDto fuel) throws SQLException;
    void updateFuel(FuelDto fuel) throws SQLException;
    void deleteFuel(Integer id) throws SQLException;
    FuelDto getFuelByBrandId(Integer brandId) throws SQLException;

}
