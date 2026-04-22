package com.example.meu_primeiro_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.meu_primeiro_springboot.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    
}
