package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.ContractDto;

import java.sql.SQLException;
import java.util.List;

public interface ContractService {
    void createContract(ContractDto contract) throws SQLException;

    void updateContract(ContractDto contract) throws SQLException;

    List<ContractDto> listContracts() throws SQLException;

    ContractDto getContractById(Integer contractId) throws SQLException;

    void deleteContract(Integer id) throws SQLException;
}
