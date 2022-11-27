package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.ContractDto;
import cu.edu.cujae.backend.core.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/")
    public ResponseEntity<List<ContractDto>> getContracts() throws SQLException {
        List<ContractDto> contractList = contractService.listContracts();
        return ResponseEntity.ok(contractList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Integer id) throws SQLException {
        ContractDto contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }

    @PostMapping("/")
    public ResponseEntity<String> createContract(@RequestBody ContractDto contract) throws SQLException {
        contractService.createContract(contract);
        return ResponseEntity.ok("Rental Contract Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateContract(@RequestBody ContractDto contract) throws SQLException {
        contractService.updateContract(contract);
        return ResponseEntity.ok("Rental Contract Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable Integer id) throws SQLException {
        contractService.deleteContract(id);
        return ResponseEntity.ok("Rental Contract deleted");
    }
}
