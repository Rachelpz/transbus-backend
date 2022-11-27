package cu.edu.cujae.backend.service;


import cu.edu.cujae.backend.core.dto.DriverDto;
import cu.edu.cujae.backend.core.dto.RequestDto;
import cu.edu.cujae.backend.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private static RequestServiceImpl requestservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void createRequest(RequestDto request) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call request_insert(?,?,?,?,?)}");

            CS.setDate(1, new java.sql.Date(request.getDate().getTime()));
            CS.setString(2, request.getPetitioner_name());
            CS.setInt(3, request.getGroup().getGroup_id());
            CS.setInt(4, request.getService().getService_id());
            CS.setInt(5, request.getVehicle().getVehicle_id());

            CS.executeUpdate();
        }
    }

    @Override
    public void updateRequest(RequestDto request) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call request_update(?,?,?,?,?,?,?)}");

            CS.setInt(1, request.getRequest_id());
            CS.setDate(2, new java.sql.Date(request.getDate().getTime()));
            CS.setString(3, request.getPetitioner_name());
            CS.setInt(4, request.getGroup().getGroup_id());
            CS.setInt(5, request.getService().getService_id());
            CS.setInt(6, request.getVehicle().getVehicle_id());
            CS.setString(7, request.getRequest_name());

            CS.executeUpdate();
        }

    }

    @Override
    public List<RequestDto> listRequests() throws SQLException {
        List<RequestDto> requestList = new ArrayList<RequestDto>();

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM request join touristic_group on touristic_group.group_id=request.touristic_group join service on service.service_id=request.service join vehicle on vehicle.vehicle_id=request.vehicle order by vehicle.plate_numb");

            while(rs.next()){
                RequestDto request = new RequestDto(
                        rs.getInt("request_id"),
                        rs.getDate("request_date"),
                        rs.getString("petitioner_name"),
                        groupService.getGroupById(rs.getInt("touristic_group")),
                        serviceService.getServiceById(rs.getInt("service")),
                        vehicleService.getVehicleById(rs.getInt("vehicle")),
                        rs.getString("request_name")
                );
                requestList.add(request);
            }
        }

        return requestList;
    }

    @Override
    public RequestDto getRequestById(Integer requestId) throws SQLException {
        RequestDto request = null;

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM request join touristic_group on touristic_group.group_id=request.touristic_group join service on service.service_id=request.service join vehicle on vehicle.vehicle_id=request.vehicle where request_id=? order by vehicle.plate_numb");

            pstmt.setInt(1, requestId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                request = new RequestDto(
                        rs.getInt("request_id"),
                        rs.getDate("request_date"),
                        rs.getString("petitioner_name"),
                        groupService.getGroupById(rs.getInt("touristic_group")),
                        serviceService.getServiceById(rs.getInt("service")),
                        vehicleService.getVehicleById(rs.getInt("vehicle")),
                        rs.getString("request_name")
                );
            }
        }

        return request;
    }

    @Override
    public void deleteRequest(Integer id) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call request_delete(?)}");

            CS.setInt(1, id);
            CS.executeUpdate();
        }
    }
}