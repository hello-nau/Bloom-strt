package com.hcc.controllerTest;
import com.hcc.controllers.AssignmentController;
import com.hcc.controllers.AuthController;
import com.hcc.controllers.requests.AuthCredentialsRequest;
import com.hcc.controllers.responses.AssignmentResponseDto;
import com.hcc.controllers.responses.LoginResponse;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.exceptions.ResourceNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.UserRepository;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssignmentControllerTest {
    @Autowired
    AssignmentController assignmentController;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthController authController;

    private String authToken;
    private Long assignmentId;

    @BeforeEach
    @Transactional
    public void setup() {
        userService.createUser("testUser", "password");
        AuthCredentialsRequest credentials = new AuthCredentialsRequest("testUser", "password");
        ResponseEntity<LoginResponse> loginResponse = authController.login(credentials);

        if (loginResponse.getBody() == null) {
            throw new RuntimeException("Login failed, response body is null");
        }
        authToken = loginResponse.getBody().getToken();

        Assignment assignment = new Assignment();
        assignment.setUser(userRepository.findByUsername("testUser").get());
        assignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION);
        assignmentService.saveAssignment(assignment);
        assignmentId = assignment.getId();

    }
    @AfterEach
    @Transactional
    public void tearDown() {
        Assignment assignment = assignmentRepository.findById(assignmentId).get();
        assignmentService.deleteAssignment(assignment);
        userService.deleteUser(userRepository.findByUsername("testUser").get());

    }
    @Test
    @Transactional
    public void testGetAssignmentById() {
        User user = userService.getUser("testUser").get();

        ResponseEntity<AssignmentResponseDto> response  = assignmentController.getAssignmentById(assignmentId, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(assignmentId, response.getBody().getAssignment().getId());
    }
    @Test
    @Transactional
    public void testGetAssignmentByIdFails() {
        User user = userService.getUser("testUser").get();

        assertThrows(ResourceNotFoundException.class, () -> {
            assignmentController.getAssignmentById(1L, user);
        });
    }

    @Test
    @Transactional
    public void testEditAssignmentById() throws AccessDeniedException {
        User user = userService.getUser("testUser").get();
        Assignment updatedAssignment = new Assignment();

        updatedAssignment.setStatus(AssignmentStatusEnum.SUBMITTED);
        assignmentController.editAssignmentById(assignmentId, updatedAssignment, user);

        assertEquals(assignmentService.findAssignmentById(assignmentId).get().getStatus(),
                AssignmentStatusEnum.SUBMITTED);
    }

    @Test
    @Transactional
    public void testEditAssignmentByIdAccessDenied() throws AccessDeniedException {
        userService.createUser("tuser", "password");
        User user = userService.getUser("tuser").get();

        Assignment updatedAssignment = new Assignment();
        updatedAssignment.setStatus(AssignmentStatusEnum.SUBMITTED);

        assertThrows(AccessDeniedException.class, () -> {
            assignmentController.editAssignmentById(assignmentId, updatedAssignment, user);
        });
    }
    @Test
    @Transactional
    public void testEditAssignmentByIdResourceNotFound() throws AccessDeniedException {
        User user = userService.getUser("testUser").get();

        Assignment updatedAssignment = new Assignment();
        updatedAssignment.setStatus(AssignmentStatusEnum.SUBMITTED);

        assertThrows(ResourceNotFoundException.class, () -> {
            assignmentController.editAssignmentById(123L, updatedAssignment, user);
        });
    }

    @Test
    @Transactional
    public void addAssignment() {
        User user = userService.getUser("testUser").get();
        Assignment assignment = new Assignment();
        assignment.setStatus(AssignmentStatusEnum.SUBMITTED);
        ResponseEntity<Assignment> response  = assignmentController.addAssignment(assignment, user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(assignment.getStatus(), AssignmentStatusEnum.SUBMITTED);
        assertEquals(user.getId(), response.getBody().getUser().getId());
    }

    private HttpHeaders createHeaders(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authToken);
        return headers;
    }

}
