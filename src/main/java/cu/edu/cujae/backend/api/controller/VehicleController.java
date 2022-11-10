package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.VehicleDto;
import cu.edu.cujae.backend.core.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    public ResponseEntity<List<VehicleDto>> getVehicles() throws SQLException {
        List<VehicleDto> vehicleList = vehicleService.listVehicles();
        return ResponseEntity.ok(vehicleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Integer id) throws SQLException {
        VehicleDto vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/")
    public ResponseEntity<String> createVehicle(@RequestBody VehicleDto vehicle) throws SQLException {
        vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok("Vehicle Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateVehicle(@RequestBody VehicleDto vehicle) throws SQLException {
        vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok("Vehicle Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id) throws SQLException {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted");
    }
}
