package com.metadata.schoolregistration.repository;

import com.metadata.schoolregistration.domain.Course;
import com.metadata.schoolregistration.domain.Student;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Set<Student> findByCourses_id(Long courseId);

    Set<Student> findByCourses(Course course);

}
