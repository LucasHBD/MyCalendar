package com.example.meu_primeiro_springboot.controller;

import java.io.IOException;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.meu_primeiro_springboot.model.Memoria;
import com.example.meu_primeiro_springboot.service.MemoriaService;
import tools.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/memorias")
public class MemoriaController {

    private final MemoriaService memoriaService;

    public MemoriaController(MemoriaService memoriaService) {
        this.memoriaService = memoriaService;
    }

    @GetMapping
    public List<Memoria> listarMemorias() {
        return memoriaService.listarMemorias();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> criarMemoria(
            @RequestPart("memoria") String memoriaJson,
            @RequestPart("arquivoImagem") MultipartFile arquivoImagem) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Memoria memoria = mapper.readValue(memoriaJson, Memoria.class);

            Memoria novaMemoria = memoriaService.salvarMemoria(memoria, arquivoImagem);

            return ResponseEntity.ok(novaMemoria);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMemoria(@PathVariable Long id){
        memoriaService.deletarMemoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<Resource> visualizarImagem(@PathVariable Long id) throws IOException {

        Memoria memoria = memoriaService.buscarPorId(id).get();

        Path caminho = Paths.get(memoria.getCaminhoImagem());
        Resource resource = new UrlResource(caminho.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

}
