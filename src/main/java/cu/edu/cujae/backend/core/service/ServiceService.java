package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.ServiceDto;

import java.sql.SQLException;
import java.util.List;

public interface ServiceService {
    void createService(ServiceDto service) throws SQLException;

    void updateService(ServiceDto service) throws SQLException;

    List<ServiceDto> listServices() throws SQLException;

    ServiceDto getServiceById(Integer serviceId) throws SQLException;

    void deleteService(Integer id) throws SQLException;
}
