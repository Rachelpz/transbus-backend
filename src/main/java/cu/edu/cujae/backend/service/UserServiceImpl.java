package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.UserDto;
import cu.edu.cujae.backend.core.service.RoleService;
import cu.edu.cujae.backend.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

	@Override
	public void createUser(UserDto user) throws SQLException {

		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
					"{call usuario_insert(?, ?, ?, ?)}");

			CS.setString(1, user.getUsername());
			CS.setString(2, user.getFullName());
			CS.setString(3, getMd5Hash(user.getPassword()));
			//CS.setString(5, user.getEmail());
			//CS.setString(6, user.getIdentification());

			CS.setInt(4, user.getRole().getId() );
			CS.executeUpdate();
		}


	}

	@Override
	public List<UserDto> listUsers() throws SQLException {
		List<UserDto> userList = new ArrayList<UserDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM usuario join role on role.role_id=usuario.role order by role.role_name");

			while(rs.next()){
				userList.add(new UserDto(rs.getInt("user_id")
						,rs.getString("user_name")
						,rs.getString("full_user_name")
						,rs.getString("pass")
						,roleService.getRoleById(rs.getInt("role"))));

			}
		}
		return userList;
	}

	@Override
	public void updateUser(UserDto user) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(
					"update usuario set user_name = ?, full_user_name = ? where user_id = ?");

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFullName());

			pstmt.setInt(3, user.getId());

			pstmt.executeUpdate();
		}
	}

	@Override
	public UserDto getUserById(Integer userId) throws SQLException {

		UserDto user = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM usuario where user_id = ?");

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				user = new UserDto(rs.getInt("user_id")
						,rs.getString("user_name")
						,rs.getString("full_user_name")
						,rs.getString("pass")

						,roleService.getRoleById(rs.getInt("role")));
			}
		}

		return user;
	}

	@Override
	public void deleteUser(Integer userId) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
					"{call usuario_delete(?)}");

			CS.setInt(1, userId);
			CS.executeUpdate();
		}
	}

	private String getMd5Hash(String chain) {
		MessageDigest md5;
		String retorno = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(chain.getBytes());
			byte[] keys = md5.digest();
			retorno = new String(new BASE64Encoder().encode(keys));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		return retorno;
	}
}
