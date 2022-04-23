package com.metadata.schoolregistration.sevice;

import com.metadata.schoolregistration.domain.Course;
import com.metadata.schoolregistration.domain.Student;
import com.metadata.schoolregistration.dto.CourseDTO;
import com.metadata.schoolregistration.dto.StudentDTO;
import com.metadata.schoolregistration.repository.CourseRepository;
import com.metadata.schoolregistration.repository.StudentRepository;
import com.metadata.schoolregistration.util.BeanUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Set<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BeanUtils.getStudentsDTO(students);
    }

    public StudentDTO getStudentById(Long id) {
        Student student = fetchAndValidateStudent(id);
        return BeanUtils.getStudentDTO(student);
    }

    public StudentDTO create(StudentDTO studentDTO) {
        Student student = BeanUtils.getStudent(studentDTO);
        student.setId(null); // auto-increment
        Student savedStudent = studentRepository.save(student);
        return BeanUtils.getStudentDTO(savedStudent);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student studentToUpdate = fetchAndValidateStudent(id);
        studentToUpdate.setFirstName(studentDTO.getFirstName());
        studentToUpdate.setLastName(studentDTO.getLastName());
        studentRepository.save(studentToUpdate);
        return BeanUtils.getStudentDTO(studentToUpdate);
    }

    public void delete(Long id) {
        fetchAndValidateStudent(id);
        studentRepository.deleteById(id);
    }

    public Set<CourseDTO> getCoursesByStudentId(Long studentId) {
        Set<Course> courses = courseRepository.findByStudents_id(studentId);
        return BeanUtils.getCoursesDTO(courses);
    }

    @Transactional
    public Set<CourseDTO> registerStudentToCourses(Long studentId, Set<Long> courseIds) {
        Student student = fetchAndValidateStudent(studentId);
        List<Course> newCoursesList = courseRepository.findAllById(courseIds);
        Set<Course> newCourses = new HashSet<>(newCoursesList);

        validateMaxCoursesPerStudents(newCourses);
        validateMaxStudentsPerCourse(newCourses);

        student.setCourses(newCourses);
        studentRepository.save(student);

        return BeanUtils.getCoursesDTO(newCourses);
    }

    public Set<StudentDTO> getAllUnregisteredStudents() {
        Set<Student> students = studentRepository.findByCourses(null);
        if (students.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BeanUtils.getStudentsDTO(students);
    }

    private Student fetchAndValidateStudent(Long id) {
        Optional<Student> studentsData = studentRepository.findById(id);
        if (studentsData.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentsData.get();
    }

    private void validateMaxCoursesPerStudents(Set<Course> courses) {
        if (courses.size() > 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A student can register to 5 course maximum");
        }
    }

    private void validateMaxStudentsPerCourse(Set<Course> courses) {
        for (var course : courses) {
            Set<Student> students = course.getStudents();

            if (students != null && students.size() > 50) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A course has 50 students maximum");
            }
        }
    }

}
