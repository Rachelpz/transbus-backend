package cu.edu.cujae.backend.service;


import cu.edu.cujae.backend.core.dto.DriverDto;
import cu.edu.cujae.backend.core.service.BrandService;
import cu.edu.cujae.backend.core.service.DriverService;
import cu.edu.cujae.backend.core.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements  DriverService {
    private static DriverServiceImpl driverservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private BrandService brandService;

    @Override
    public void createDriver(DriverDto driver) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call driver_insert(?,?,?,?,?,?)}");

        CS.setString(1, driver.getDriver_name());
        CS.setString(2, driver.getDni());
        CS.setString(3, driver.getAddress());
        CS.setString(4, driver.getPhone_number());
        CS.setInt(5, driver.getDistrict().getDistrict_id());
        CS.setInt(6, driver.getBrand().getBrand_id());

        CS.executeUpdate();
    }

    @Override
    public void updateDriver(DriverDto driver) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call driver_update(?,?,?,?,?,?,?)}");

        CS.setInt(1, driver.getDriver_id());
        CS.setString(2, driver.getDriver_name());
        CS.setString(3, driver.getDni());
        CS.setString(4, driver.getAddress());
        CS.setString(5, driver.getPhone_number());
        CS.setInt(6, driver.getDistrict().getDistrict_id());
        CS.setInt(7, driver.getBrand().getBrand_id());

        CS.executeUpdate();
    }

    @Override
    public List<DriverDto> listDrivers() throws SQLException {
        List<DriverDto> driverList = new ArrayList<DriverDto>();
        ResultSet rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(
                "SELECT * FROM driver join district on district.district_id=driver.district_id join brand on brand.brand_id=driver.brand_id");

        while(rs.next()){
            DriverDto driver = new DriverDto(
                    rs.getInt("driver_id"),
                    rs.getString("driver_name"),
                    rs.getString("dni"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    districtService.getDistrictById(rs.getInt("district_id")),
                    brandService.getBrandById(rs.getInt("brand_id"))
            );
            driverList.add(driver);
        }

        return driverList;
    }

    @Override
    public DriverDto getDriverById(Integer driverId) throws SQLException {
        DriverDto driver = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM driver join district on district.district_id=driver.district_id join brand on brand.brand_id=driver.brand_id where driver_id=?");

        pstmt.setInt(1, driverId);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            driver = new DriverDto(
                    rs.getInt("driver_id"),
                    rs.getString("driver_name"),
                    rs.getString("dni"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    districtService.getDistrictById(rs.getInt("district_id")),
                    brandService.getBrandById(rs.getInt("brand_id"))
            );
        }

        return driver;
    }

    @Override
    public void deleteDriver(Integer id) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call driver_delete(?)}");

        CS.setInt(1, id);
        CS.executeUpdate();
    }
}