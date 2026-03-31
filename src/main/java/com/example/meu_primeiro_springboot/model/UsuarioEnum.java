package com.example.meu_primeiro_springboot.model;

public enum UsuarioEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    UsuarioEnum(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
