package com.school.domain.service;

import com.school.domain.model.StudentModel;
import com.school.api.models.StudentDTO;
import com.school.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    public final StudentRepository studentRepository;
    public final PhotoService photoService;

    public StudentService(StudentRepository studentRepository, PhotoService photoService) {
        this.studentRepository = studentRepository;
        this.photoService = photoService;
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentModel> student = studentRepository.findAll();
        return listToDto(student);
    }

    public StudentDTO newStudent(StudentDTO studentDTO) {
        StudentModel studentModel = new StudentModel();
        studentModel.setNome(studentDTO.getNome());
        studentModel.setSobrenome(studentDTO.getSobrenome());
        studentModel.setEmail(studentDTO.getEmail());
        studentModel.setIdade(studentDTO.getIdade());
        studentModel.setAltura(studentDTO.getAltura());
        studentModel.setPeso(studentDTO.getPeso());
        studentModel.setCreated_at(new Date());
        studentModel.setUpdated_at(new Date());
        studentRepository.save(studentModel);
        return toDTO(studentModel);
    }

    public Object updateStudent(StudentDTO studentDTO) {
        Optional<StudentModel> student = studentRepository.findById(studentDTO.getId());
        if(student.isPresent()) {
            StudentModel studentModel = student.get();
            studentModel.setNome(studentDTO.getNome());
            studentModel.setSobrenome(studentDTO.getSobrenome());
            studentModel.setEmail(studentDTO.getEmail());
            studentModel.setIdade(studentDTO.getIdade());
            studentModel.setAltura(studentDTO.getAltura());
            studentModel.setPeso(studentDTO.getPeso());
            studentModel.setUpdated_at(new Date());
            studentRepository.save(studentModel);
            return toDTO(studentModel);
        }
        return null;
    }

    public Object getStudentById(int id) {
        Optional<StudentModel> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return toDTO(student.get());
        }
        return null;
    }

    public void deleteStudent(Integer id) throws IOException {
        photoService.deletePhotoByStudentId(id);
        studentRepository.deleteById(id);
    }

    private List<StudentDTO> listToDto(List<StudentModel> student) {
        List<StudentDTO> studentDTO = new ArrayList<>();
        for (StudentModel studentModel : student) {
            StudentDTO studentDTO1 = new StudentDTO();
            studentDTO1.setId(studentModel.getId());
            studentDTO1.setNome(studentModel.getNome());
            studentDTO1.setSobrenome(studentModel.getSobrenome());
            studentDTO1.setEmail(studentModel.getEmail());
            studentDTO1.setIdade(studentModel.getIdade());
            studentDTO1.setAltura(studentModel.getAltura());
            studentDTO1.setPeso(studentModel.getPeso());
            studentDTO1.setFotos(photoService.getPhotosByStudentId(studentModel.getId()));
            studentDTO.add(studentDTO1);
        }
        return studentDTO;
    }

    private StudentDTO toDTO(StudentModel studentModel) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentModel.getId());
        studentDTO.setNome(studentModel.getNome());
        studentDTO.setSobrenome(studentModel.getSobrenome());
        studentDTO.setEmail(studentModel.getEmail());
        studentDTO.setIdade(studentModel.getIdade());
        studentDTO.setAltura(studentModel.getAltura());
        studentDTO.setPeso(studentModel.getPeso());
        studentDTO.setFotos(photoService.getPhotosByStudentId(studentModel.getId()));
        return studentDTO;
    }



}
