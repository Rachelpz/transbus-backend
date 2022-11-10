package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleService {
    void createVehicle(VehicleDto vehicle) throws SQLException;

    void updateVehicle(VehicleDto vehicle) throws SQLException;

    List<VehicleDto> listVehicles() throws SQLException;

    VehicleDto getVehicleById(Integer vehicleId) throws SQLException;

    void deleteVehicle(Integer id) throws SQLException;
}
