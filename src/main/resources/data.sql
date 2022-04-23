DROP TABLE IF EXISTS student_course;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;

CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id) 
);

CREATE TABLE IF NOT EXISTS course (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id) 
);

CREATE TABLE IF NOT EXISTS student_course (
    student_id BIGINT,
    course_id BIGINT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

INSERT INTO student (id, first_name, last_name) VALUES (1, 'Albert', 'Einstein');
INSERT INTO student (id, first_name, last_name) VALUES (2, 'Nikola', 'Tesla');
INSERT INTO student (id, first_name, last_name) VALUES (3, 'Marie', 'Curie');

INSERT INTO course (id, name) VALUES (1, 'Quantum Pysics');
INSERT INTO course (id, name) VALUES (2, 'Chemistry');

INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (2, 1);
INSERT INTO student_course (student_id, course_id) VALUES (3 ,2);

commit;

