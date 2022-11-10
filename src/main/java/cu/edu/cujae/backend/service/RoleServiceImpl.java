package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.FuelDto;
import cu.edu.cujae.backend.core.dto.RoleDto;
import cu.edu.cujae.backend.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public RoleDto getRoleByUserId(Integer userId) throws SQLException {
		RoleDto role=new RoleDto();


		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT role_id, role_name, access_level FROM role inner join usuario on usuario.role = role.role_id where usuario.user_id = ?");

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				role = new RoleDto(rs.getInt("role_id")
						, rs.getString("role_name")
						,rs.getInt("access_level"));

			}
		}
		return role;
	}

	@Override
	public List<RoleDto> listRoles() throws SQLException {
		List<RoleDto> roleList = new ArrayList<RoleDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM role");

			while(rs.next()){
				roleList.add(new RoleDto(rs.getInt("role_id")
						,rs.getString("role_name")
						,rs.getInt("access_level")));
			}
		}
		return roleList;
	}

	@Override
	public RoleDto getRoleById(Integer roleId) throws SQLException {
		RoleDto role = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM role where role_id = ?");

			pstmt.setLong(1, roleId);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				role = new RoleDto(rs.getInt("role_id")
						,rs.getString("role_name")
						,rs.getInt("access_level"));
			}
		}

		return role;
	}
}