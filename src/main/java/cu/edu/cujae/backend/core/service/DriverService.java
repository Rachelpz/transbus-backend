package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.DriverDto;

import java.sql.SQLException;
import java.util.List;

public interface DriverService {
    void createDriver(DriverDto driver) throws SQLException;

    void updateDriver(DriverDto driver) throws SQLException;

    List<DriverDto> listDrivers() throws SQLException;

    DriverDto getDriverById(Integer driverId) throws SQLException;

    void deleteDriver(Integer id) throws SQLException;
}
