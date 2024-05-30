package com.example.les12model.controller;

import com.example.les12model.model.Teacher;
import com.example.les12model.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository repos;

    public TeacherController(TeacherRepository repos) {
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Teacher>> getTeachersByLastName(@RequestParam String lname) {
        return ResponseEntity.ok(repos.findByLastNameContainingIgnoreCase(lname));
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        try {
            this.repos.save(teacher);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + teacher.getId()).toUriString());
            return ResponseEntity.created(uri).body(teacher);
        }
        catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Creation failed");
        }
    }
}
