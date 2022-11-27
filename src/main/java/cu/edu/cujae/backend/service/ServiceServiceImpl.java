package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.ServiceDto;
import cu.edu.cujae.backend.core.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    private static ServiceServiceImpl serviceservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createService(ServiceDto service) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call service_insert(?,?,?,?,?,?)}");

            CS.setString(1, service.getService_name());
            CS.setString(2, service.getPickup_place());
            CS.setTimestamp(3, new java.sql.Timestamp(service.getPickup_time().getTime()));
            CS.setFloat(4, service.getKm_traveled());
            CS.setFloat(5, service.getSpent_fuel());
            CS.setString(6, service.getService_type());

            CS.executeUpdate();
        }
    }

    @Override
    public void updateService(ServiceDto service) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call service_update(?,?,?,?,?,?,?)}");

            CS.setInt(1, service.getService_id());
            CS.setString(2, service.getService_name());
            CS.setString(3, service.getPickup_place());
            CS.setTimestamp(4, new java.sql.Timestamp(service.getPickup_time().getTime()));
            CS.setFloat(5, service.getKm_traveled());
            CS.setFloat(6, service.getSpent_fuel());
            CS.setString(7, service.getService_type());

            CS.executeUpdate();
        }

    }

    @Override
    public List<ServiceDto> listServices() throws SQLException {
        List<ServiceDto> serviceList = new ArrayList<ServiceDto>();

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM service ORDER BY service.service_id");

            while(rs.next()){
                ServiceDto service = new ServiceDto(
                        rs.getInt("service_id"),
                        rs.getString("service_name"),
                        rs.getString("pickup_place"),
                        rs.getTime("pickup_time"),
                        rs.getFloat("km_traveled"),
                        rs.getFloat("spent_fuel"),
                        rs.getString("service_type")
                );
                serviceList.add(service);
            }
        }

        return serviceList;
    }

    @Override
    public ServiceDto getServiceById(Integer serviceId) throws SQLException {
        ServiceDto service = null;

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM service WHERE service_id=?");

            pstmt.setInt(1, serviceId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                service = new ServiceDto(
                        rs.getInt("service_id"),
                        rs.getString("service_name"),
                        rs.getString("pickup_place"),
                        rs.getTime("pickup_time"),
                        rs.getFloat("km_traveled"),
                        rs.getFloat("spent_fuel"),
                        rs.getString("service_type")
                );
            }
        }

        return service;
    }

    @Override
    public void deleteService(Integer id) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call service_delete(?)}");

            CS.setInt(1, id);
            CS.executeUpdate();
        }
    }
}
