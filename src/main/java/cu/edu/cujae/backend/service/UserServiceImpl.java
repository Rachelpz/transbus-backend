package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.RoleDto;
import cu.edu.cujae.backend.core.dto.UserDto;
import cu.edu.cujae.backend.core.service.RoleService;
import cu.edu.cujae.backend.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RoleService roleService;

//	@Override
//	public void createUser(UserDto user) throws SQLException {
//
//		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
//			CallableStatement CS = conn.prepareCall(
//					"{call create_user(?, ?, ?, ?, ?, ?, ?)}");
//
//			CS.setString(1, UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
//			CS.setString(2, user.getUsername());
//			CS.setString(3, user.getFullName());
//			CS.setString(4, encodePass(user.getPassword()));
//			CS.setString(5, user.getEmail());
//			CS.setString(6, user.getIdentification());
//
//			//roles separados por coma, ej: "1, 2, 4"
//			String roles = user.getRoles().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
//			CS.setString(7, roles);
//			CS.executeUpdate();
//		}
//
//
//	}

	@Override
	public void createUser(UserDto user) throws SQLException{
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into xuser values (?, ?, ?, ?, ?, ?)"
			);
			String user_id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getFullName());
			pstmt.setString(4, encodePass(user.getPassword()));
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getIdentification());

			pstmt.executeUpdate();

			for (RoleDto role: user.getRoles()) {
				 pstmt = conn.prepareStatement("insert into user_role values (?, ?)");
				 pstmt.setString(1, user_id);
				 pstmt.setInt(2, role.getId());
				 pstmt.executeUpdate();
			}
		}
	}

	private String encodePass(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public List<UserDto> listUsers() throws SQLException {
		List<UserDto> userList = new ArrayList<UserDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM xuser");

			while(rs.next()){
				userList.add(new UserDto(rs.getString("id")
						,rs.getString("username")
						,rs.getString("full_name")
						,rs.getString("password")
						,rs.getString("email")
						,rs.getString("identification")
						,roleService.getRolesByUserId(rs.getString("id"))));
			}
		}
		return userList;
	}

	@Override
	public void updateUser(UserDto user) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			String password = user.getPassword().isEmpty() ? getUserById(user.getId()).getPassword() : encodePass(user.getPassword());

			PreparedStatement pstmt = conn.prepareStatement(
					"update xuser set username = ?, full_name = ?, email = ?, identification = ?, password = ? where id = ?");

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFullName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getIdentification());
			pstmt.setString(5, password);
			pstmt.setString(6, user.getId());

			pstmt.executeUpdate();

			if (user.getRoles() != null) {
				if (user.getRoles().size() != 0) {
					pstmt = conn.prepareStatement("delete from user_role where user_id = ?");
					pstmt.setString(1, user.getId());
					pstmt.executeUpdate();

					for (RoleDto role: user.getRoles()) {
						pstmt = conn.prepareStatement("insert into user_role values (?, ?)");
						pstmt.setString(1, user.getId());
						pstmt.setInt(2, role.getId());
						pstmt.executeUpdate();
					}
				}
			}
		}
	}

	@Override
	public UserDto getUserById(String userId) throws SQLException {

		UserDto user = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM xuser where id = ?");

			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				user = new UserDto(rs.getString("id")
						,rs.getString("username")
						,rs.getString("full_name")
						,rs.getString("password")
						,rs.getString("email")
						,rs.getString("identification")
						,roleService.getRolesByUserId(rs.getString("id")));
			}
		}

		return user;
	}

	@Override
	public void deleteUser(String userId) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
					"{call delete_user(?)}");

			CS.setString(1, userId);
			CS.executeUpdate();
		}
	}

	@Override
	public UserDto getUserByUsername(String username) throws SQLException {
		UserDto user = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM xuser where username = ?");

			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				user = new UserDto(rs.getString("id")
						,rs.getString("username")
						,rs.getString("full_name")
						,rs.getString("password")
						,rs.getString("email")
						,rs.getString("identification")
						,roleService.getRolesByUserId(rs.getString("id")));
			}
		}

		return user;
	}

}
