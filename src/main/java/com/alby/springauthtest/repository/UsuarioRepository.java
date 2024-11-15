package com.alby.springauthtest.repository;

import com.alby.springauthtest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // encontrar un usuario email
    Optional<Usuario> findOneByEmail(String email);

    // encontrar un usuario por su DNI
    Optional<Usuario> findOneByDni(String dni);

    // encontrar un usuario por username
    Optional<Usuario> findOneByUsername(String username);
}
