package com.youx.clinica.controlador;

import com.youx.clinica.dto.LoginRequestDTO;
import com.youx.clinica.dto.LoginResponseDTO;
import com.youx.clinica.modelo.Usuario;
import com.youx.clinica.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginControlador {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<Usuario> usuario = usuarioRepositorio.findByLogin(loginRequestDTO.login());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (!usuario.get().isLoginCorreto(loginRequestDTO, bCryptPasswordEncoder)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
        }

        Instant expiracao = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        String scope = usuario.get().getRole().toString();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("api-clinica")
                .subject(usuario.get().getLogin())
                .expiresAt(expiracao)
                .claim("scope", scope)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiracao));
    }
}
