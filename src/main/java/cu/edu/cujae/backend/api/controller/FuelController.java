package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.FuelDto;
import cu.edu.cujae.backend.core.service.Fuel_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fuels")
public class FuelController {
    @Autowired
    private Fuel_TypeService fuelService;

    @GetMapping("/")
    public ResponseEntity<List<FuelDto>> getFuels() throws SQLException {
        List<FuelDto> fuelList = fuelService.listFuels();
        return ResponseEntity.ok(fuelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelDto> getFuelById(@PathVariable Integer id) throws SQLException {
        FuelDto fuel = fuelService.getFuelById(id);
        return ResponseEntity.ok(fuel);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody FuelDto fuel) throws SQLException {
        fuelService.createFuel(fuel);
        return ResponseEntity.ok("Fuel Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody FuelDto fuel) throws SQLException {
        fuelService.updateFuel(fuel);
        return ResponseEntity.ok("Fuel Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        fuelService.deleteFuel(id);
        return ResponseEntity.ok("Fuel deleted");
    }
}
