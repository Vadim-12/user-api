package com.example.user.api.controllers;

import com.example.user.api.models.User;
import com.example.user.api.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private final UserService service = mock(UserService.class);
    private final UserController controller = new UserController(service);

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(List.of(new User(), new User()));
        assertEquals(2, controller.getAll().size());
    }

    @Test
    void testGetFound() {
        User user = new User();
        user.setId(1L);
        when(service.getById(1L)).thenReturn(user);

        ResponseEntity<User> response = controller.get(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetNotFound() {
        when(service.getById(99L)).thenReturn(null);
        ResponseEntity<User> response = controller.get(99L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreate() {
        User user = new User();
        when(service.save(user, null)).thenReturn(user);
        User created = controller.create(user, null);
        assertEquals(user, created);
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1L)).thenReturn(null);
        ResponseEntity<User> response = controller.update(1L, new User(), null);
        assertEquals(404, response.getStatusCodeValue());
    }
}