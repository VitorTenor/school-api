package com.school.api.controller;

import com.school.api.models.PhotoRegisterDTO;
import com.school.domain.repository.PhotoRepository;
import com.school.domain.repository.StudentRepository;
import com.school.domain.service.PhotoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fotos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoRepository photoRepository;
    private final StudentRepository studentRepository;

    public PhotoController(PhotoService photoService, PhotoRepository photoRepository, StudentRepository studentRepository) {
        this.photoService = photoService;
        this.photoRepository = photoRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/")
    @ApiOperation(value = "Register a Student Photo")
    public ResponseEntity<Object> uploadPhoto(@RequestParam("foto") MultipartFile file,
                            @RequestParam("aluno_id") int id) throws IOException {
        if(studentRepository.existsById(id)){
            photoService.uploadPhoto(new PhotoRegisterDTO(file, id));
            return ResponseEntity.status(HttpStatus.OK).body("Foto cadastrada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
    }
    @GetMapping("/{name}")
    @ApiOperation(value = "Get a Student Photo")
    public ResponseEntity<byte[]> getPhotosByFileName(@PathVariable(value = "name") String fileName) throws IOException {
        if (photoRepository.findByFileName(fileName) != null) {
            return photoService.getPhotoByFileName(fileName);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto nao encontrada".getBytes());
    }
}
