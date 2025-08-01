package com.example.user.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    private String middleName;

    @Past
    private LocalDate birthDate;

    @Email
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String phoneNumber;

    private String photoPath;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public long getId() {
        return this.id;
    }
}
