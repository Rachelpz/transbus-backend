package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.LoginRequestDto;
import cu.edu.cujae.backend.core.dto.UserAuthenticatedDto;
import cu.edu.cujae.backend.core.dto.UserDto;
import cu.edu.cujae.backend.core.email.Mail;
import cu.edu.cujae.backend.core.security.TokenProvider;
import cu.edu.cujae.backend.core.service.UserService;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "Authentication endpoint controller")
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
	private UserService userService;
    
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequestDto loginRequestDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = tokenProvider.createToken(authentication);
			
			UserDto user = userService.getUserByUsername(loginRequestDto.getUsername());
			UserAuthenticatedDto userAuth = new UserAuthenticatedDto(user.getId(), user.getUsername(), user.getFullName(), null, 
					user.getEmail(), user.getIdentification(), user.getRoles(), token);
			
			return ResponseEntity.ok(userAuth);
		} catch (BadCredentialsException | SQLException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
		}
	}


    
}
