package com.metadata.schoolregistration.util;

import com.metadata.schoolregistration.domain.Course;
import com.metadata.schoolregistration.domain.Student;
import com.metadata.schoolregistration.dto.CourseDTO;
import com.metadata.schoolregistration.dto.StudentDTO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanUtils {

    public static Set<StudentDTO> getStudentsDTO(List<Student> students) {
        return getStudentsDTO(new HashSet<>(students));
    }

    public static Set<StudentDTO> getStudentsDTO(Set<Student> students) {
        var studentsDTO = new HashSet<StudentDTO>();
        for (var student : students) {
            studentsDTO.add(getStudentDTO(student));
        }
        return studentsDTO;
    }

    public static StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = StudentDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
        return studentDTO;
    }

    public static Set<CourseDTO> getCoursesDTO(List<Course> courses) {
        return getCoursesDTO(new HashSet<>(courses));
    }

    public static Set<CourseDTO> getCoursesDTO(Set<Course> courses) {
        Set<CourseDTO> coursesDTO = new HashSet<>();
        for (var course : courses) {
            coursesDTO.add(getCourseDTO(course));
        }
        return coursesDTO;
    }

    public static CourseDTO getCourseDTO(Course course) {
        CourseDTO courseDTO = CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .build();
        return courseDTO;
    }

    public static Student getStudent(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.getId())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .build();
    }

    public static Set<Course> getCourses(Set<CourseDTO> coursesDTO) {
        Set<Course> courses = new HashSet<>();
        for (var courseDTO : coursesDTO) {
            courses.add(getCourse(courseDTO));
        }
        return courses;
    }

    public static Course getCourse(CourseDTO courseDTO) {
        Course course = Course.builder()
                .id(courseDTO.getId())
                .name(courseDTO.getName())
                .build();
        return course;
    }

}
