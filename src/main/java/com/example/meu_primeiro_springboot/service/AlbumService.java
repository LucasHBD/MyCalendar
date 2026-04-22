package com.example.meu_primeiro_springboot.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.meu_primeiro_springboot.model.Album;
import com.example.meu_primeiro_springboot.repository.AlbumRepository;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    
    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    public List<Album> listarAlbuns(){
        return albumRepository.findAll();
    }

    public Album salvarAlbum(Album album) throws IOException{
        return albumRepository.save(album);
    }

    public void deletarAlbum(Long id){
        albumRepository.deleteById(id);
    }

    public Optional<Album> buscarPorId(Long id){
        return albumRepository.findById(id);
    }
}
