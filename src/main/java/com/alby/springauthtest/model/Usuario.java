package com.alby.springauthtest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
    @Column(length = 8, unique = true, nullable = false)
    private String dni;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 6, max = 30, message = "El nombre de usuario debe tener entre 6 y 30 caracteres")
    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Email(message = "Debe proporcionar un email válido")
    @NotBlank(message = "El email es obligatorio")
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "clave", nullable = false)
    private String clave;
}
