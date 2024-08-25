package com.hcc.controllers;

import com.hcc.controllers.responses.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    AssignmentResponseDto assignmentResponseDto;
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById() {
        return ResponseEntity.ok(new AssignmentResponseDto(new Assignment()));
    }
    @PutMapping("/{id}")
    public void editAssignmentById() {

        return;
    }
    @PostMapping("/")
    public ResponseEntity<Assignment> addAssignment() {
        return ResponseEntity.ok(new Assignment());
    }
}
