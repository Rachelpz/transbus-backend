package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.DistrictDto;
import cu.edu.cujae.backend.core.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("/")
    public ResponseEntity<List<DistrictDto>> getDistricts() throws SQLException {
        List<DistrictDto> driverList = districtService.listDistricts();
        return ResponseEntity.ok(driverList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictDto> getDistrictById(@PathVariable Integer id) throws SQLException {
        DistrictDto district = districtService.getDistrictById(id);
        return ResponseEntity.ok(district);
    }

    @PostMapping("/")
    public ResponseEntity<String> createDistrict(@RequestBody DistrictDto district) throws SQLException {
        districtService.createDistrict(district);
        return ResponseEntity.ok("District Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateDistrict(@RequestBody DistrictDto district) throws SQLException {
        districtService.updateDistrict(district);
        return ResponseEntity.ok("District Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDistrict(@PathVariable Integer id) throws SQLException {
        districtService.deleteDistrict(id);
        return ResponseEntity.ok("District deleted");
    }
}
