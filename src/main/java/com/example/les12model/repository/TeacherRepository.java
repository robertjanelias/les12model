package com.example.les12model.repository;

import com.example.les12model.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByLastNameContainingIgnoreCase(String lname);
}
