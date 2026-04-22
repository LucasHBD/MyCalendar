package com.example.meu_primeiro_springboot.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "albuns")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy= "album", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Memoria> memorias;

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public List<Memoria> getMemorias(){
        return memorias;
    }

    public void setMemorias(List<Memoria> memorias){
        this.memorias = memorias;
    }
}
