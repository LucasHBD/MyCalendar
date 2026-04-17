package com.example.meu_primeiro_springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albuns")
@CrossOrigin(origins = "*")
public class AlbumController {
    
    @GetMapping
    public List<Album> listarAlbuns(){
        return albumRepository.findAll();
    }
}
