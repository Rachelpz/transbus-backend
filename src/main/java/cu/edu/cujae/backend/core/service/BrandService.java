package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.BrandDto;

import java.sql.SQLException;
import java.util.List;

public interface BrandService {
    void createBrand(BrandDto brand) throws SQLException;

    void updateBrand(BrandDto brand) throws SQLException;

    List<BrandDto> listBrands() throws SQLException;

    BrandDto getBrandById(Integer brandId) throws SQLException;

    void deleteBrand(Integer id) throws SQLException;
}
