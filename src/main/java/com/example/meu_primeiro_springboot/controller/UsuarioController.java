package com.example.meu_primeiro_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meu_primeiro_springboot.infra.security.TokenService;
import com.example.meu_primeiro_springboot.model.AuthenticationDTO;
import com.example.meu_primeiro_springboot.model.LoginResponseDTO;
import com.example.meu_primeiro_springboot.model.RegisterDTO;
import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        if(this.usuarioRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario novoUsuario = new Usuario(data.login(), encryptedPassword, data.role());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
    
}
