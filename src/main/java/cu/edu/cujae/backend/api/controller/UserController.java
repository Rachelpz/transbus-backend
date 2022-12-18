package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.RoleDto;
import cu.edu.cujae.backend.core.dto.UserDto;
import cu.edu.cujae.backend.core.email.EmailSenderService;
import cu.edu.cujae.backend.core.email.Mail;
import cu.edu.cujae.backend.core.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private EmailSenderService emailSenderService;

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
    public ResponseEntity<String> create(@RequestBody UserDto user) throws SQLException ,MessagingException, IOException, TemplateException{
        ResponseEntity<String> response = ResponseEntity.ok("User Created");

        try {
            userService.createUser(user);
            emailSenderService.sendEmail(user);
        } catch (SQLException e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("A user with that username already exists");
        }

        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto user) throws SQLException ,MessagingException, IOException, TemplateException{
        List<RoleDto> roles = new ArrayList<>();
        roles.add(new RoleDto(2, "", ""));
        user.setRoles(roles);
        userService.createUser(user);
        emailSenderService.sendEmail(user);
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

