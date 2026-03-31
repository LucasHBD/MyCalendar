package com.example.meu_primeiro_springboot.service;

import java.io.IOException;
import java.lang.foreign.Linker.Option;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.meu_primeiro_springboot.model.Memoria;
import com.example.meu_primeiro_springboot.repository.MemoriaRepository;

import java.nio.file.Path;

@Service
public class MemoriaService {

    private final MemoriaRepository memoriaRepository;

    // Caminho da pasta onde as imagens serão salvas
    private final String caminhoImagem = 
        "/home/lucashbd/Área de trabalho/meu-primeiro-springboot/media/";

    public MemoriaService(MemoriaRepository memoriaRepository) {
        this.memoriaRepository = memoriaRepository;
    }

    public List<Memoria> listarMemorias() {
        return memoriaRepository.findAll();
    }

    public Optional<Memoria> buscarPorId(Long id){
        return memoriaRepository.findById(id);
    }

    public Memoria salvarMemoria(Memoria memoria, MultipartFile arquivoImagem) throws IOException {

        if (!arquivoImagem.isEmpty()) {

            // Gera nome único para evitar conflito
            String nomeArquivo = System.currentTimeMillis() + "_" + arquivoImagem.getOriginalFilename();

            Path caminho = Paths.get(caminhoImagem + nomeArquivo);

            memoria.setCaminhoImagem(caminho.toString());

            Files.createDirectories(caminho.getParent());
            Files.write(caminho, arquivoImagem.getBytes());

            memoria.setNomeImagem(nomeArquivo);
        }

        return memoriaRepository.save(memoria);
    }

    public void deletarMemoria(Long id){
        memoriaRepository.deleteById(id);
    }
}
