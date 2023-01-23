package com.myapi.api.controllers;

import com.myapi.api.models.dtos.StudentDTO;
import com.myapi.api.repositories.StudentRepository;
import com.myapi.api.services.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class StudentController {

    public final StudentService studentService;
    public final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }
    @GetMapping
    @ApiOperation(value = "Get All Students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @PostMapping("/")
    @ApiOperation(value = "Create a new Student")
    public ResponseEntity<Object> newStudent(@RequestBody StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email já cadastrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentService.newStudent(studentDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a Student")
    public ResponseEntity<Object> updateStudent(@PathVariable int id,
                                              @RequestBody StudentDTO studentDTO) {
        if (studentRepository.existsById(id)) {
            studentDTO.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(studentDTO));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Student by ID")
    public ResponseEntity<Object> getStudentById(@PathVariable(value = "id") int id) {


        if (studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Student by ID")
    public ResponseEntity<Object> deleteStudent(@PathVariable(value = "id") Integer id) throws IOException {
        if (studentRepository.existsById(id)) {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
    }
}
