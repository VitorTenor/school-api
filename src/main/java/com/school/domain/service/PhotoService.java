package com.school.domain.service;

import com.school.domain.model.PhotoModel;
import com.school.api.models.PhotoDTO;
import com.school.api.models.PhotoRegisterDTO;
import com.school.domain.repository.PhotoRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void uploadPhoto(PhotoRegisterDTO photoRegisterDTO) throws IOException {
        String newFileName = generateRandomName();
        byte[] bytes = photoRegisterDTO.getFile().getBytes();
        Path path = Paths.get("src/main/resources/media/photos/" + newFileName);
        Files.write(path, bytes);
        photoRegisterDTO.setOriginalName(photoRegisterDTO.getFile().getOriginalFilename());
        photoRegisterDTO.setFileName(newFileName);
        savePhotoBD(photoRegisterDTO);
    }

    public ArrayList<PhotoDTO> getPhotosByStudentId(int id) {
        ArrayList<PhotoModel> photos = photoRepository.findAllByStudentId(id);
        ArrayList<PhotoDTO> photoDTO = listToDTO(photos);
        return photoDTO;
    }

    public ResponseEntity<byte[]> getPhotoByFileName(String fileName) throws IOException {
        PhotoModel photo = photoRepository.findByFileName(fileName);
        byte[] image = Files.readAllBytes(Paths.get("src/main/resources/media/photos/" + photo.getFilename()));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    private void savePhotoBD(PhotoRegisterDTO photoRegisterDTO) {
        PhotoModel newPhoto = new PhotoModel();
        newPhoto.setAluno_id(photoRegisterDTO.getAluno_id());
        newPhoto.setFilename(photoRegisterDTO.getFileName());
        newPhoto.setOriginalname(photoRegisterDTO.getOriginalName());
        newPhoto.setCreated_at(new Date());
        newPhoto.setUpdated_at(new Date());
        photoRepository.save(newPhoto);
    }


    public void deletePhotoByStudentId(Integer id) throws IOException {
        ArrayList<PhotoDTO> photos = getPhotosByStudentId(id);
        for (PhotoDTO photo : photos) {
            photoRepository.deleteById(photo.getId());
            Path path = Paths.get("src/main/resources/media/photos/" + photo.getFileName());
            Files.delete(path);
        }
    }


    private String generateRandomName() {
        Instant instant = Instant.now();
        long randomValue = new Random().nextLong();
        String generatedName = instant.toString() + "_" + Long.toString(randomValue);
        String newName = generatedName.replace(":", "_").replace(".", "_");
        newName = newName + ".jpg";
        return newName;
    }

    private ArrayList<PhotoDTO> listToDTO(ArrayList<PhotoModel> photos) {
        ArrayList<PhotoDTO> photosDTO = new ArrayList<>();
        for (PhotoModel photo : photos) {
            PhotoDTO newPhotoDTO = new PhotoDTO();
            newPhotoDTO.setId(photo.getId());
            newPhotoDTO.setFileName(photo.getFilename());
            newPhotoDTO.setUrl("http://localhost:8080/fotos/" + photo.getFilename());
            photosDTO.add(newPhotoDTO);
        }
        return photosDTO;
    }

}
