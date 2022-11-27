package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.RequestDto;

import java.sql.SQLException;
import java.util.List;

public interface RequestService {
    void createRequest(RequestDto request) throws SQLException;

    void updateRequest(RequestDto request) throws SQLException;

    List<RequestDto> listRequests() throws SQLException;

    RequestDto getRequestById(Integer requestFdrId) throws SQLException;

    void deleteRequest(Integer id) throws SQLException;
}
