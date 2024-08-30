package com.hcc.controllers;

import com.hcc.controllers.responses.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.services.UserDetailServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    UserDetailServiceImpl userDetails;

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable Long assignmentId,
                                                                   @AuthenticationPrincipal User user) {
        // see if i can retrieve the user authentification this way?
//        User user = (User) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
        return ResponseEntity.ok(new AssignmentResponseDto(new Assignment()));
    }
    @PutMapping("/{id}")
    public void editAssignmentById(@PathVariable Long assignmentId, @RequestBody Assignment assignment,
                                   @AuthenticationPrincipal User user) {
        //set the old id assignment to a new one
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
        }

    @PostMapping("/")
    public ResponseEntity<Assignment> addAssignment( @RequestBody Assignment assignment,
                                                     @AuthenticationPrincipal User user ) {
        return ResponseEntity.ok(assignment);
    }
}
