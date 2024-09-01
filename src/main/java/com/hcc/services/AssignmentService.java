package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Optional<Assignment> findAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }
    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
}
