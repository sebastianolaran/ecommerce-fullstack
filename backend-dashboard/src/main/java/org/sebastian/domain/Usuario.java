package org.sebastian.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



@Data
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
@Table(name = "usuario")
public class Usuario implements UserDetails {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;


    public Usuario() {

    }


    public Usuario(Long id_usuario, String username, String password, String email) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Usuario(String nombreUsuario, String contrasenia, String correo) {
        this.username = nombreUsuario;
        this.password = contrasenia;
        this.email = correo;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
