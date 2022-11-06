package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.BrandDto;
import cu.edu.cujae.backend.core.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;



@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<List<BrandDto>> getBrands() throws SQLException {
        List<BrandDto> brandList = brandService.listBrands();
        return ResponseEntity.ok(brandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Integer id) throws SQLException {
        BrandDto brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody BrandDto brand) throws SQLException {
        brandService.createBrand(brand);
        return ResponseEntity.ok("Brand Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody BrandDto brand) throws SQLException {
        brandService.updateBrand(brand);
        return ResponseEntity.ok("Brand Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        brandService.deleteBrand(id);
        return ResponseEntity.ok("Brand deleted");
    }
}
