package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.BrandDto;
import cu.edu.cujae.backend.core.dto.FuelDto;
import cu.edu.cujae.backend.core.dto.RoleDto;
import cu.edu.cujae.backend.core.service.Fuel_TypeService;
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
public class Fuel_TypeServiceImpl implements Fuel_TypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FuelDto> listFuels() throws SQLException {
        List<FuelDto> fuelList = new ArrayList<FuelDto>();
        ResultSet rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(
                "SELECT * FROM fuel_type");

        while(rs.next()){
            FuelDto fuel = new FuelDto(rs.getInt("fuel_type_id")
                    ,rs.getString("fuel_type_name"));
            fuelList.add(fuel);
        }

        return fuelList;
    }

    @Override
    public FuelDto getFuelById(Integer FuelId) throws SQLException{
        FuelDto fuel = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM fuel_type where fuel_type_id = ?");

        pstmt.setInt(1, FuelId);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            fuel = new FuelDto(rs.getInt("fuel_type_id")
                    ,rs.getString("fuel_type_name")

            );
        }

        return fuel;
    }


    @Override
    public FuelDto getFuelByBrandId(Integer userId) throws SQLException {
        FuelDto fuel = new FuelDto();
        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT fuel_type_id, fuel_type_name FROM fuel_type inner join brand on brand.fuel_type = fuel_type.fuel_type_id where brand.brand_id = ?");

        pstmt.setInt(1, userId);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            fuel = new FuelDto(rs.getInt("fuel_type_id")
                    ,rs.getString("fuel_type_name"));
        }
        return fuel;
    }

    @Override
    public void createFuel(FuelDto fuel) throws SQLException{
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call fuel_insert(?)}");

        CS.setString(1, fuel.getFuel_name());
        CS.executeUpdate();
    }

    @Override
    public void updateFuel(FuelDto fuel) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call fuel_update(?,?)}");
        CS.setInt(1, fuel.getFuel_id());
        CS.setString(2, fuel.getFuel_name());

        CS.executeUpdate();
    }

    @Override
    public void deleteFuel(Integer fuelId) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call fuel_delete(?)}");

        CS.setInt(1, fuelId);
        CS.executeUpdate();
    }
}
