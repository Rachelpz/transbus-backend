package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.RequestDto;
import cu.edu.cujae.backend.core.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/")
    public ResponseEntity<List<RequestDto>> getRequests() throws SQLException {
        List<RequestDto> requestList = requestService.listRequests();
        return ResponseEntity.ok(requestList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequestById(@PathVariable Integer id) throws SQLException {
        RequestDto request = requestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/")
    public ResponseEntity<String> createRequest(@RequestBody RequestDto request) throws SQLException {
        requestService.createRequest(request);
        return ResponseEntity.ok("Request Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateRequest(@RequestBody RequestDto request) throws SQLException {
        requestService.updateRequest(request);
        return ResponseEntity.ok("Request Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Integer id) throws SQLException {
        try {
        requestService.deleteRequest(id);
        return ResponseEntity.ok("Request deleted");
        }catch (SQLException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some SQL exception occured");
        }
    }
}
