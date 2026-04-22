package com.example.meu_primeiro_springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.meu_primeiro_springboot.model.Album;
import com.example.meu_primeiro_springboot.service.AlbumService;


@RestController
@RequestMapping("/api/albuns")
@CrossOrigin(origins = "*")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> listarAlbuns(){
        return albumService.listarAlbuns();
    }

    @PostMapping
    public ResponseEntity<?> criarAlbum(@RequestPart("album") Album album) {
        try {
            Album novoAlbum = albumService.salvarAlbum(album);
            return ResponseEntity.ok(novoAlbum);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlbum(@PathVariable Long id){
        albumService.deletarAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
