package cu.edu.cujae.backend.service;


import cu.edu.cujae.backend.core.dto.BrandDto;
import cu.edu.cujae.backend.core.dto.FuelDto;
import cu.edu.cujae.backend.core.service.BrandService;
import cu.edu.cujae.backend.core.service.Fuel_TypeService;
import cu.edu.cujae.backend.core.service.RoleService;
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
public class BrandServiceImpl implements BrandService {
    private static BrandServiceImpl brandservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Fuel_TypeService fuelService;

    @Override
    public void createBrand(BrandDto brand) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call brand_insert(?,?,?,?)}");

        CS.setString(1, brand.getBrand_name());
        CS.setInt(2, brand.getSeats_numb().intValue());
        CS.setInt(3, brand.getFuel_type().getFuel_id());
        CS.setDouble(4, brand.getFuel_consumtion());

        CS.executeUpdate();
    }


    @Override
    public void updateBrand(BrandDto brand) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call brand_update(?,?,?,?,?)}");
        CS.setInt(1, brand.getBrand_id());
        CS.setString(2, brand.getBrand_name());
        CS.setInt(3, brand.getSeats_numb());
        CS.setDouble(5, brand.getFuel_consumtion());
        CS.setInt(4, brand.getFuel_type().getFuel_id());

         CS.executeUpdate();
    }


    @Override
    public void deleteBrand(Integer brandId) throws SQLException{
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call brand_delete(?)}");

        CS.setInt(1, brandId);
        CS.executeUpdate();
    }

    @Override
    public List<BrandDto> listBrands() throws SQLException {
        List<BrandDto> brandList = new ArrayList<BrandDto>();
        ResultSet rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(
                "SELECT * FROM brand join fuel_type on fuel_type.fuel_type_id=brand.fuel_type order by fuel_type.fuel_type_name");

        while(rs.next()){
            BrandDto brand = new BrandDto(rs.getInt("brand_id")
                    , rs.getString("brand_name")
                    , rs.getInt("seats_numb")
                    , rs.getDouble("fuel_consumtion")
                    , fuelService.getFuelByBrandId(rs.getInt("brand_id")));
            brandList.add(brand);
        }

        return brandList;
    }

    @Override
    public BrandDto getBrandById(Integer brandId) throws SQLException {

        BrandDto brand = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "Select * from brand join fuel_type on fuel_type.fuel_type_id=brand.fuel_type where brand_id=?");

        pstmt.setInt(1, brandId);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            brand = new BrandDto(rs.getInt("brand_id")
                    ,rs.getString("brand_name")
                    , rs.getInt("seats_numb")
                    , rs.getDouble("fuel_consumtion")
                    , fuelService.getFuelByBrandId(rs.getInt("brand_id")));
        }

        return brand;
    }
}
