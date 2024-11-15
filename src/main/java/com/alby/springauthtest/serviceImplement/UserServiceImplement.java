package com.alby.springauthtest.serviceImplement;

import com.alby.springauthtest.model.Usuario;
import com.alby.springauthtest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));
        System.out.println("Usuario encontrado: " + usuario.getEmail()); // Log para depuración
        return new UserDetailImplement(usuario);
    }

    // Método para registrar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar si el email ya está en uso
        if (usuarioRepository.findOneByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        // Verificar si el DNI ya está en uso
        if (usuarioRepository.findOneByDni(usuario.getDni()).isPresent()) {
            throw new IllegalArgumentException("El DNI ya está en uso.");
        }

        // Verificar si el username ya está en uso
        if (usuarioRepository.findOneByUsername(usuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        // Hashear la contraseña antes de guardar el usuario
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }
}
