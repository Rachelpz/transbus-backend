package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.RoleDto;
import cu.edu.cujae.backend.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
    public ResponseEntity<List<RoleDto>> getRoles() throws SQLException {
		List<RoleDto> roleList = roleService.listRoles();
        return ResponseEntity.ok(roleList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<RoleDto> geRoleById(@PathVariable Integer id) throws SQLException {
		RoleDto role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }
	
	@GetMapping("/users/{userId}")
    public ResponseEntity<RoleDto> geRoleByUserId(@PathVariable Integer userId) throws SQLException {
		RoleDto roleList = roleService.getRoleByUserId(userId);
        return ResponseEntity.ok(roleList);
    }
	
}
