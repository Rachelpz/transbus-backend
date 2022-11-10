package cu.edu.cujae.backend.core.dto;

import java.util.List;

public class UserDto {
	private Integer id;
	private String username;
	private String fullName;
	private String password;
	//private String email;
	//private String identification;
	private RoleDto role;

	public UserDto() {
		super();
	}

	public UserDto(Integer id, String username, String fullName, String password,
			RoleDto role) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.password = password;

		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
}
