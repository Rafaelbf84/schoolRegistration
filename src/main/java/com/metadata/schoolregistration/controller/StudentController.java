package com.metadata.schoolregistration.controller;

import com.metadata.schoolregistration.dto.CourseDTO;
import com.metadata.schoolregistration.dto.StudentDTO;
import com.metadata.schoolregistration.sevice.StudentService;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> getAllStudents() {
        Set<StudentDTO> studentsDTO = studentService.getAllStudents();
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudentDTO = studentService.create(studentDTO);
        return new ResponseEntity<>(createdStudentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudentDTO = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(updatedStudentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<CourseDTO>> getCoursesRegistered(@PathVariable("id") Long id) {
        Set<CourseDTO> coursesDTO = studentService.getCoursesByStudentId(id);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Set<CourseDTO>> registerStudentToCourses(@PathVariable("id") Long studentId, @RequestBody Set<Long> courseIds) {
        Set<CourseDTO> coursesDTO = studentService.registerStudentToCourses(studentId, courseIds);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping("/unregistered")
    public ResponseEntity<Set<StudentDTO>> getAllUnregisteredStudents() {
        Set<StudentDTO> studentsDTO = studentService.getAllUnregisteredStudents();
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

}
