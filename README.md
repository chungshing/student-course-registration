# Student Course Registration System

A simple Java Spring Boot application manage students, courses, and course registrations.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- H2 Database

## Database

This project uses an H2 in-memory database.

The database schema consists of:

- `STUDENT`
- `COURSE`
- `STUDENT_COURSE`

Example  schema:

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE course (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    duration VARCHAR(50)
);

CREATE TABLE student_course (
    student_course_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    UNIQUE (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);
```

## Run

1. Run the Spring Boot application.
2. Access the application in your browser.
3. Open the H2 Console to view the database.
