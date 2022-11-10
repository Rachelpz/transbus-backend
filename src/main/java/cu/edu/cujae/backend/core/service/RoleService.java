package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.RoleDto;

public interface RoleService {

	RoleDto getRoleByUserId(Integer userId) throws SQLException;

	List<RoleDto> listRoles() throws SQLException;

	RoleDto getRoleById(Integer roleId) throws SQLException;

}
