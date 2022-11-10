package cu.edu.cujae.backend.core.dto;

public class RoleDto {
	private Integer id;
	private String roleName;
	private Integer access_level;
	
	public RoleDto(Integer id, String roleName, Integer description) {
		this.id = id;
		this.roleName = roleName;
		this.access_level = access_level;
	}
	
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getAccess_level() {
		return access_level;
	}
	public void setAccess_level(Integer access_level) {
		this.access_level = access_level;
	}
}
