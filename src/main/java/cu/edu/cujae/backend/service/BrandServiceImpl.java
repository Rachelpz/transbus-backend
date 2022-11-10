package cu.edu.cujae.backend.service;


import cu.edu.cujae.backend.core.dto.BrandDto;
import cu.edu.cujae.backend.core.service.BrandService;
import cu.edu.cujae.backend.core.service.Fuel_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.*;
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
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement CS = conn.prepareCall(
                    "{call brand_insert(?,?,?,?)}");


            CS.setString(1, brand.getBrand_name());
            CS.setInt(2, brand.getSeats_numb());
            CS.setInt(3, brand.getFuel_type().getFuel_id());
            CS.setInt(4, brand.getFuel_consumption());

            CS.executeUpdate();
        }
    }

    @Override
    public void updateBrand(BrandDto brand) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                    "update brand set brand_name = ?, seats_numb = ?, fuel_type = ?, fuel_consumtion = ? where brand_id = ?");

            pstmt.setInt(5, brand.getBrand_id());
            pstmt.setString(1, brand.getBrand_name());
            pstmt.setInt(2, brand.getSeats_numb());
            pstmt.setInt(4, brand.getFuel_consumption());
            pstmt.setInt(3, brand.getFuel_type().getFuel_id());

            pstmt.executeUpdate();
        }
    }


    @Override
    public void deleteBrand(Integer brandId) throws SQLException{
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement CS = conn.prepareCall(
                    "{call brand_delete(?)}");


            CS.setInt(1, brandId);
            CS.executeUpdate();
        }
    }

    @Override
    public List<BrandDto> listBrands() throws SQLException {
        List<BrandDto> brandList = new ArrayList<BrandDto>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM brand join fuel_type on fuel_type.fuel_type_id=brand.fuel_type order by fuel_type.fuel_type_name");

            while (rs.next()) {
                BrandDto brand = new BrandDto(rs.getInt("brand_id")
                        , rs.getString("brand_name")
                        , rs.getInt("seats_numb")
                        , rs.getInt("fuel_consumtion")
                        , fuelService.getFuelById(rs.getInt("fuel_type")));
                brandList.add(brand);
            }
        }
            return brandList;

    }
    @Override
    public BrandDto getBrandById(Integer brandId) throws SQLException {

        BrandDto brand = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                    "Select * from brand join fuel_type on fuel_type.fuel_type_id=brand.fuel_type where brand_id=?");

            pstmt.setInt(1, brandId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                brand = new BrandDto(rs.getInt("brand_id")
                        , rs.getString("brand_name")
                        , rs.getInt("seats_numb")
                        , rs.getInt("fuel_consumtion")
                        , fuelService.getFuelById(rs.getInt("fuel_type")));
            }
        }
        return brand;
    }
}
