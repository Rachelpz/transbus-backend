package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.DistrictDto;

import java.sql.SQLException;
import java.util.List;

public interface DistrictService {
    void createDistrict(DistrictDto district) throws SQLException;

    void updateDistrict(DistrictDto district) throws SQLException;

    List<DistrictDto> listDistricts() throws SQLException;

    DistrictDto getDistrictById(Integer districtId) throws SQLException;

    void deleteDistrict(Integer id) throws SQLException;
}
