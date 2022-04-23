package com.metadata.schoolregistration.controller;

import com.metadata.schoolregistration.dto.CourseDTO;
import com.metadata.schoolregistration.dto.StudentDTO;
import com.metadata.schoolregistration.sevice.CourseService;
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
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Set<CourseDTO>> getAllCourses() {
        Set<CourseDTO> coursesDTO = courseService.getAllCourses();
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourseDTO = courseService.create(courseDTO);
        return new ResponseEntity<>(createdCourseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourseDTO = courseService.updateCourse(id, courseDTO);
        return new ResponseEntity<>(updatedCourseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<StudentDTO>> getStudentsResgistered(@PathVariable("id") Long id) {
        Set<StudentDTO> studentsDTO = courseService.getStudentsByCourseId(id);
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping("/empty")
    public ResponseEntity<Set<CourseDTO>> getAllEmptyCourses() {
        Set<CourseDTO> coursesDTO = courseService.getAllEmptyCourses();
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

}
