package com.hcc.controllers;

import com.hcc.controllers.responses.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.exceptions.ResourceNotFoundException;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserDetailServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
//    @Autowired
//    UserDetailServiceImpl userDetails;
    @Autowired
    AssignmentService assignmentService;
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable Long assignmentId,
                                                                   @AuthenticationPrincipal User user) {
        Optional<Assignment> assignment = assignmentService.findAssignmentById(assignmentId);
        if(assignment.isEmpty()) {
            throw new ResourceNotFoundException("The assignment with the id " + assignmentId + " was not found.");
        }
        //TODO check if the assignment belongs to a current user or the current user is reviewer
        return ResponseEntity.ok(new AssignmentResponseDto(assignment.get()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> editAssignmentById(@PathVariable Long assignmentId, @RequestBody Assignment updatedAssignment,
                                   @AuthenticationPrincipal User user) throws AccessDeniedException {
        Optional<Assignment> existingAssignment = assignmentService.findAssignmentById(assignmentId);
        if(existingAssignment.isEmpty()) {
            throw new ResourceNotFoundException("The assignment with the id " + assignmentId + " was not found.");
        }
        if(!existingAssignment.get().getUser().equals(user)) {
            throw new AccessDeniedException("You are not authorized to edit this assignment.");
        }
        existingAssignment.get().setStatus(updatedAssignment.getStatus());
        existingAssignment.get().setNumber(updatedAssignment.getNumber());
        existingAssignment.get().setGithubUrl(updatedAssignment.getGithubUrl());
        existingAssignment.get().setBranch(updatedAssignment.getBranch());
        existingAssignment.get().setReviewVideoUrl(updatedAssignment.getReviewVideoUrl());

        assignmentService.saveAssignment(existingAssignment.get());

        return ResponseEntity.noContent().build();
}

//        @PutMapping("{assignmentId}")
//        public ResponseEntity<?> updateAssignment(@PathVariable Long assignmentId,
//                                                  @RequestBody Assignment assignment,
//                                                  @AuthenticationPrincipal User user) {
//
//            // add the code reviewer to the assignment if it was claimed
//            if (assignment.getCodeReviewer() != null) {
//                User codeReviewer = assignment.getCodeReviewer();
//                codeReviewer = userService.findUserByUsername(codeReviewer.getUsername()).get();
//
//                if (AuthorityUtil.hasRole(AuthorityEnum.ROLE_REVIEWER.name(), codeReviewer)) {
//                    assignment.setCodeReviewer(codeReviewer);
//                }
//            }
//
//            Assignment updatedAssignment = assignmentService.save(assignment);
//            return ResponseEntity.ok(updatedAssignment);


    @PostMapping("/")
    public ResponseEntity<Assignment> addAssignment( @RequestBody Assignment assignment,
                                                     @AuthenticationPrincipal User user ) {
        assignment.setUser(user);

        Assignment savedAssignment = assignmentService.saveAssignment(assignment);

        return ResponseEntity
                .created(URI.create("/api/assignments/" + savedAssignment.getId()))
                .body(savedAssignment);
    }

}
// see if i can retrieve the user authentification this way?
//        User user = (User) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();