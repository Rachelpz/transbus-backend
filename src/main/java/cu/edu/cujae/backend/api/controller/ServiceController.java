package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.ServiceDto;
import cu.edu.cujae.backend.core.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/")
    public ResponseEntity<List<ServiceDto>> getServices() throws SQLException {
        List<ServiceDto> serviceList = serviceService.listServices();
        return ResponseEntity.ok(serviceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Integer id) throws SQLException {
        ServiceDto service = serviceService.getServiceById(id);
        return ResponseEntity.ok(service);
    }

    @PostMapping("/")
    public ResponseEntity<String> createService(@RequestBody ServiceDto service) throws SQLException {
        serviceService.createService(service);
        return ResponseEntity.ok("Service Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateService(@RequestBody ServiceDto service) throws SQLException {
        serviceService.updateService(service);
        return ResponseEntity.ok("Service Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) throws SQLException {
        serviceService.deleteService(id);
        return ResponseEntity.ok("Service deleted");
    }
}
