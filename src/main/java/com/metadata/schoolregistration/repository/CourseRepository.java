package com.metadata.schoolregistration.repository;

import com.metadata.schoolregistration.domain.Course;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Set<Course> findByStudents_id(Long studentId);

    Set<Course> findByStudents(Object object);

}
