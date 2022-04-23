package com.metadata.schoolregistration.sevice;

import com.metadata.schoolregistration.domain.Course;
import com.metadata.schoolregistration.domain.Student;
import com.metadata.schoolregistration.dto.CourseDTO;
import com.metadata.schoolregistration.dto.StudentDTO;
import com.metadata.schoolregistration.repository.CourseRepository;
import com.metadata.schoolregistration.repository.StudentRepository;
import com.metadata.schoolregistration.util.BeanUtils;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public CourseService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Set<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BeanUtils.getCoursesDTO(courses);
    }

    public CourseDTO getCourseById(Long id) {
        Course course = fetchAndValidateCourse(id);
        return BeanUtils.getCourseDTO(course);
    }

    public CourseDTO create(CourseDTO courseDTO) {
        Course course = BeanUtils.getCourse(courseDTO);
        course.setId(null); // auto-increment
        Course savedCourse = courseRepository.save(course);
        return BeanUtils.getCourseDTO(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course courseToUpdate = fetchAndValidateCourse(id);
        courseToUpdate.setName(courseDTO.getName());
        courseRepository.save(courseToUpdate);
        return BeanUtils.getCourseDTO(courseToUpdate);
    }

    public void delete(Long id) {
        Course course = fetchAndValidateCourse(id);
        validateIfCourseHasStudents(course);
        courseRepository.deleteById(id);
    }

    public Set<CourseDTO> getAllEmptyCourses() {
        Set<Course> courses = courseRepository.findByStudents(null);
        if (courses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BeanUtils.getCoursesDTO(courses);
    }

    private Course fetchAndValidateCourse(Long id) {
        Optional<Course> coursesData = courseRepository.findById(id);
        if (coursesData.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return coursesData.get();
    }

    public Set<StudentDTO> getStudentsByCourseId(Long courseId) {
        Set<Student> students = studentRepository.findByCourses_id(courseId);
        return BeanUtils.getStudentsDTO(students);
    }

    private void validateIfCourseHasStudents(Course course) {
        Set<Student> students = course.getStudents();
        if (students != null && !students.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course has stutends regitered.");
        }
    }

}
