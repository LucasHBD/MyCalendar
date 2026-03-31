package com.example.meu_primeiro_springboot.model;

public record RegisterDTO(String login, String password, UsuarioEnum role) {

}
