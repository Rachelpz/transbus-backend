package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.GroupDto;
import cu.edu.cujae.backend.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public ResponseEntity<List<GroupDto>> getGroups() throws SQLException {
        List<GroupDto> groupList = groupService.listGroups();
        return ResponseEntity.ok(groupList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Integer id) throws SQLException {
        GroupDto group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/")
    public ResponseEntity<String> createGroup(@RequestBody GroupDto group) throws SQLException {
        groupService.createGroup(group);
        return ResponseEntity.ok("Group Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateGroup(@RequestBody GroupDto group) throws SQLException {
        groupService.updateGroup(group);
        return ResponseEntity.ok("Group Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Integer id) throws SQLException {
        try {
        groupService.deleteGroup(id);
        return ResponseEntity.ok("Group deleted");
    }catch (SQLException e)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some SQL exception occured");
    }
    }
}