package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.DriverDto;
import cu.edu.cujae.backend.core.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/")
    public ResponseEntity<List<DriverDto>> getDrivers() throws SQLException {
        List<DriverDto> driverList = driverService.listDrivers();
        return ResponseEntity.ok(driverList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriversById(@PathVariable Integer id) throws SQLException {
        DriverDto driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/")
    public ResponseEntity<String> createDriver(@RequestBody DriverDto driver) throws SQLException {
        driverService.createDriver(driver);
        return ResponseEntity.ok("Driver Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateDriver(@RequestBody DriverDto driver) throws SQLException {
        driverService.updateDriver(driver);
        return ResponseEntity.ok("Driver Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable Integer id) throws SQLException {
        try {
            driverService.deleteDriver(id);
            return ResponseEntity.ok("Driver deleted");
        }catch (SQLException e)
        {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
