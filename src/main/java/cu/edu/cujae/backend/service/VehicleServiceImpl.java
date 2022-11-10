package cu.edu.cujae.backend.service;


import cu.edu.cujae.backend.core.dto.VehicleDto;
import cu.edu.cujae.backend.core.service.BrandService;
import cu.edu.cujae.backend.core.service.DriverService;
import cu.edu.cujae.backend.core.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static VehicleServiceImpl vehicleservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DriverService driverService;

    @Autowired
    private BrandService brandService;

    @Override
    public void createVehicle(VehicleDto vehicle) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call vehicle_insert(?,?,?,?,?)}");

            CS.setFloat(1, vehicle.getPlanned_fuel());
            CS.setString(2, vehicle.getPlate_numb());
            CS.setInt(3, vehicle.getFirst_driver().getDriver_id());
            CS.setInt(4, vehicle.getSecond_driver().getDriver_id());
            CS.setInt(5, vehicle.getBrand().getBrand_id());

            CS.executeUpdate();
        }
    }

    @Override
    public void updateVehicle(VehicleDto vehicle) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call vehicle_update(?,?,?,?,?,?)}");

            CS.setInt(1, vehicle.getVehicle_id());
            CS.setFloat(2, vehicle.getPlanned_fuel());
            CS.setString(3, vehicle.getPlate_numb());
            CS.setInt(4, vehicle.getFirst_driver().getDriver_id());
            CS.setInt(5, vehicle.getSecond_driver().getDriver_id());
            CS.setInt(6, vehicle.getBrand().getBrand_id());

            CS.executeUpdate();
        }

    }

    @Override
    public List<VehicleDto> listVehicles() throws SQLException {
        List<VehicleDto> vehicleList = new ArrayList<VehicleDto>();

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM vehicle join driver on driver.driver_id=vehicle.driver1 join brand on brand.brand_id=vehicle.brand");

            while(rs.next()){
                VehicleDto vehicle = new VehicleDto(
                        rs.getInt("vehicle_id"),
                        rs.getString("plate_numb"),
                        rs.getFloat("planned_fuel"),
                        driverService.getDriverById(rs.getInt("driver1")),
                        driverService.getDriverById(rs.getInt("driver2")),
                        brandService.getBrandById(rs.getInt("brand"))
                );
                vehicleList.add(vehicle);
            }
        }

        return vehicleList;
    }

    @Override
    public VehicleDto getVehicleById(Integer vehicleId) throws SQLException {
        VehicleDto vehicle = null;

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM vehicle join driver on driver.driver_id=vehicle.driver1 join brand on brand.brand_id=vehicle.brand where vehicle_id=?");

            pstmt.setInt(1, vehicleId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                vehicle = new VehicleDto(
                        rs.getInt("vehicle_id"),
                        rs.getString("plate_numb"),
                        rs.getFloat("planned_fuel"),
                        driverService.getDriverById(rs.getInt("driver1")),
                        driverService.getDriverById(rs.getInt("driver2")),
                        brandService.getBrandById(rs.getInt("brand"))
                );
            }
        }

        return vehicle;
    }

    @Override
    public void deleteVehicle(Integer id) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call vehicle_delete(?)}");

            CS.setInt(1, id);
            CS.executeUpdate();
        }
    }
}