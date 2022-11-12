package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.GroupDto;

import java.sql.SQLException;
import java.util.List;

public interface GroupService {
    void createGroup(GroupDto group) throws SQLException;

    void updateGroup(GroupDto group) throws SQLException;

    List<GroupDto> listGroups() throws SQLException;

    GroupDto getGroupById(Integer groupId) throws SQLException;

    void deleteGroup(Integer id) throws SQLException;
}
