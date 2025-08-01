package com.example.user.api.services;

import com.example.user.api.models.User;
import com.example.user.api.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User save(User user, MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            String contentType = photo.getContentType();
            if (!isSupportedContentType(contentType)) {
                throw new IllegalArgumentException("Only JPG, JPEG and PNG image formats are supported");
            }

            String filename = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path path = Paths.get("uploads", filename);
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, photo.getBytes());
                user.setPhotoPath(path.toString());
            }catch (IOException e) {
                throw new RuntimeException("Failed to save photo", e);
            }
        }
        return repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png");
    }
}
