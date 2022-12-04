package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.UserDto;
import cu.edu.cujae.backend.core.email.Mail;
import cu.edu.cujae.backend.core.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() throws SQLException {
        List<UserDto> userList = userService.listUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) throws SQLException {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) throws SQLException {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody UserDto user) throws SQLException {
        userService.createUser(user);
//        sendMailToUserWithCredentials(user.getFullName(),user.getEmail());
        return ResponseEntity.ok("User Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody UserDto user) throws SQLException {
        userService.updateUser(user);
        return ResponseEntity.ok("User Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
//
//    private void sendMailToUserWithCredentials(String fullName, String email){
//        Mail mail=new Mail();
//        mail.setMailTo(email);
//        mail.setSubject("Registro de Usuario");
//        mail.setTemplate("user-registration-template.ftl");
//
//        Map<String,Object> model=new HashMap<String, Object>();
//        model.put("name",fullName);
//        mail.setProps(model);
//
//        try{
//            emailService.sendEmail(mail);
//        }catch(MessagingException | IOException | TemplateException e){
//            e.printStackTrace();
//        }
//
//    }
}

