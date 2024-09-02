package com.hcc.controllerTest;
import com.hcc.controllers.AssignmentController;
import com.hcc.entities.Assignment;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @BeforeEach
    public void setup() {
        Assignment assignment = new Assignment();
        assignment.setId(123456);
        assignmentService.saveAssignment(assignment);
    }
    @AfterEach
    public void tearDown() {
        Assignment assignment = assignmentRepository.findById(Long.valueOf(123456)).get();
        assignmentService.deleteAssignment(assignment);
    }
    @Test
    public void testGetAssignmentById() {
        Optional<Assignment> assignment = assignmentService.findAssignmentById(Long.valueOf(123456));
        assertTrue(assignment.isPresent());
    }

}
