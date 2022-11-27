package cu.edu.cujae.backend.service;


import cu.edu.cujae.backend.core.dto.ContractDto;
import cu.edu.cujae.backend.core.dto.DriverDto;
import cu.edu.cujae.backend.core.service.ContractService;
import cu.edu.cujae.backend.core.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private static ContractServiceImpl contractservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequestService requestService;

    @Override
    public void createContract(ContractDto contract) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call rental_contract_insert(?,?,?,?,?)}");

            CS.setDate(1, new java.sql.Date(contract.getStart_date().getTime()));
            CS.setDate(2, new java.sql.Date(contract.getEnd_date().getTime()));
            CS.setFloat(3, contract.getKm_traveled());
            CS.setFloat(4, contract.getAmount_charged());
            CS.setInt(5, contract.getRequest().getRequest_id());

            CS.executeUpdate();
        }
    }

    @Override
    public void updateContract(ContractDto contract) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call rental_contract_update(?,?,?,?,?,?)}");

            CS.setInt(1, contract.getContract_id());
            CS.setDate(2, new java.sql.Date(contract.getStart_date().getTime()));
            CS.setDate(3, new java.sql.Date(contract.getEnd_date().getTime()));
            CS.setFloat(4, contract.getKm_traveled());
            CS.setFloat(5, contract.getAmount_charged());
            CS.setInt(6, contract.getRequest().getRequest_id());

            CS.executeUpdate();
        }

    }

    @Override
    public List<ContractDto> listContracts() throws SQLException {
        List<ContractDto> contractList = new ArrayList<ContractDto>();

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM rental_contract join request on request.request_id=rental_contract.request_id order by request.request_name");

            while(rs.next()){
                ContractDto contract = new ContractDto(
                        rs.getInt("contract_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getFloat("km_traveled"),
                        rs.getFloat("amount_charged"),
                        requestService.getRequestById(rs.getInt("request_id"))
                );
                contractList.add(contract);
            }
        }

        return contractList;
    }

    @Override
    public ContractDto getContractById(Integer contractId) throws SQLException {
        ContractDto contract = null;

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM rental_contract join request on request.request_id=rental_contract.request_id where contract_id=? order by request.request_name");

            pstmt.setInt(1, contractId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                contract = new ContractDto(
                        rs.getInt("contract_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getFloat("km_traveled"),
                        rs.getFloat("amount_charged"),
                        requestService.getRequestById(rs.getInt("request_id"))
                );
            }
        }

        return contract;
    }

    @Override
    public void deleteContract(Integer id) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call rental_contract_delete(?)}");

            CS.setInt(1, id);
            CS.executeUpdate();
        }
    }
}