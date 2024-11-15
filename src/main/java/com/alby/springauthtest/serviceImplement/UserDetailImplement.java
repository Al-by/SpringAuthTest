package com.alby.springauthtest.serviceImplement;

import com.alby.springauthtest.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailImplement implements UserDetails {

    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si los roles no están implementados, devolvemos una lista vacía
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuario.getClave(); // Este es el campo que contiene la contraseña del usuario
    }

    @Override
    public String getUsername() {
        return usuario.getEmail(); // Spring usa este método para obtener el identificador del usuario (email)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes personalizar la lógica si necesitas manejar cuentas expiradas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes personalizar la lógica si necesitas manejar cuentas bloqueadas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes personalizar la lógica si necesitas manejar credenciales expiradas
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes personalizar la lógica si necesitas manejar usuarios deshabilitados
    }

    public String getNombre() {
        return usuario.getUsername(); // Método adicional para obtener el nombre del usuario
    }
}
