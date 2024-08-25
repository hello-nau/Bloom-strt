package com.hcc.controllers;

import com.hcc.entities.Assignment;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById() {
        return ResponseEntity.ok(new Assignment());
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
