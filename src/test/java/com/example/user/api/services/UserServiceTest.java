package com.example.user.api.services;

import com.example.user.api.models.User;
import com.example.user.api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final UserRepository repo = mock(UserRepository.class);
    private final UserService service = new UserService(repo);

    @Test
    void testGetById_found() {
        User user = new User();
        user.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(user));

        User result = service.getById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetById_notFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertNull(service.getById(99L));
    }

    @Test
    void testSaveWithoutPhoto() {
        User user = new User();
        when(repo.save(user)).thenReturn(user);
        User saved = service.save(user, null);
        assertEquals(user, saved);
    }

    @Test
    void testSaveInvalidPhoto() {
        User user = new User();
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getContentType()).thenReturn("image/gif");
        assertThrows(IllegalArgumentException.class, () -> service.save(user, file));
    }
}