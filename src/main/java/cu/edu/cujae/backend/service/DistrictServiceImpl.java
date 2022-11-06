package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.DistrictDto;
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
public class DistrictServiceImpl implements DistrictService {
    private static DistrictServiceImpl districtservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createDistrict(DistrictDto district) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call district_insert(?)}");

        CS.setString(1, district.getDistrict_name());

        CS.executeUpdate();
    }

    @Override
    public void updateDistrict(DistrictDto district) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call district_update(?,?)}");

        CS.setInt(1, district.getDistrict_id());
        CS.setString(2, district.getDistrict_name());

        CS.executeUpdate();
    }

    @Override
    public List<DistrictDto> listDistricts() throws SQLException {
        List<DistrictDto> districtList = new ArrayList<DistrictDto>();
        ResultSet rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(
                "SELECT * FROM district");

        while(rs.next()){
            DistrictDto district = new DistrictDto(
                    rs.getInt("district_id"),
                    rs.getString("district_name")
            );

            districtList.add(district);
        }

        return districtList;
    }

    @Override
    public DistrictDto getDistrictById(Integer districtId) throws SQLException {
        DistrictDto district = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM district where district_id=?");

        pstmt.setInt(1, districtId);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            district = new DistrictDto(
                    rs.getInt("district_id"),
                    rs.getString("district_name")
            );
        }

        return district;
    }

    @Override
    public void deleteDistrict(Integer id) throws SQLException {
        CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
                "{call district_delete(?)}");

        CS.setInt(1, id);
        CS.executeUpdate();
    }
}
