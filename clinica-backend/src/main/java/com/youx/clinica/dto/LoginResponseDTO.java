package com.youx.clinica.dto;

import java.time.Instant;

public record LoginResponseDTO(String token, Instant expiracao) {

}
