package com.hcc.controllers;

import com.hcc.controllers.responses.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.services.UserDetailServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    UserDetailServiceImpl userDetails;
    @Autowired
    AssignmentResponseDto assignmentResponseDto;
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById() {
        // see if i can retrieve the user authentification this way?
//        User user = (User) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
        return ResponseEntity.ok(new AssignmentResponseDto(new Assignment()));
    }
    @PutMapping("/{id}")
    public void editAssignmentById(AssignmentResponseDto currentAssignment, AssignmentResponseDto editedAssignment) {
        Assignment assignment = currentAssignment.getAssignment();
        assignment.setBranch(editedAssignment.getAssignment().getBranch());
        assignment.setCodeReviewer(editedAssignment.getAssignment().getCodeReviewer());
        assignment.setGithubUrl(editedAssignment.getAssignment().getGithubUrl());
        assignment.setReviewVideoUrl(editedAssignment.getAssignment().getReviewVideoUrl());
        currentAssignment.setAssignment(assignment);
    }
    @PostMapping("/")
    public ResponseEntity<Assignment> addAssignment(Assignment assignment) {
        return ResponseEntity.ok(assignment);
    }
}
