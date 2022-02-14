// package com.example.demo.student;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping(path = "api/v1/students")
// public class StudentController {
// private final StudentService studentService;

// @Autowired // this is what makes the dependency injection
// public StudentController(StudentService studentService) {
// this.studentService = studentService;
// }

// @GetMapping
// public List<Student> getStudents() {
// return studentService.getStudents();
// }
// }
