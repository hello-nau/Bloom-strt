package com.hcc.controllerTest;
import com.hcc.controllers.AuthController;
import com.hcc.controllers.requests.AuthCredentialsRequest;
import com.hcc.controllers.responses.LoginResponse;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
//import org.junit.Test;
import com.hcc.services.UserService;
import com.hcc.utils.CustomPasswordEncoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
public class AuthControllerTest {
    @Autowired
    private AuthController authController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @BeforeEach
    public void setUp() {
        userService.createUser("name", "password");
    }
    @AfterEach
    public void tearDown() {
        userService.deleteUser(userRepository.findByUsername("name").get());
    }
    @Test
    public void testFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername("name");
        assertTrue(foundUser.isPresent());
    }
    @Test
    public void testLoginSuccess() {
        AuthCredentialsRequest request = new AuthCredentialsRequest("name", "password");
        ResponseEntity<LoginResponse> response = authController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getToken());

    }
    @Test
    public void testLoginFailure() {
        AuthCredentialsRequest request = new AuthCredentialsRequest("unknown", "password");
        ResponseEntity<LoginResponse> response = authController.login(request);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
    @Test
    public void testValidateTokenValid() {
        AuthCredentialsRequest request = new AuthCredentialsRequest("name", "password");
        ResponseEntity<LoginResponse> loginResponse = authController.login(request);
        String token = loginResponse.getBody().getToken();
        Map<String, Boolean> response = authController.validateToken("Bearer " + token).getBody();
        assertTrue(response.get("isValid"));
    }
    @Test
    public void testValidateTokenInvalid() {
        ResponseEntity<Map<String, Boolean>> response = authController.validateToken("InvalidToken");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
