package com.example.meu_primeiro_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.meu_primeiro_springboot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    UserDetails findByLogin(String login);
}
