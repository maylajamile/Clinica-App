package com.youx.clinica.dto;

import com.youx.clinica.modelo.Enfermeiro;
import com.youx.clinica.modelo.Paciente;

public record EnfermeiroResponseDTO (String id, String nome, String cpf){
    public EnfermeiroResponseDTO(Enfermeiro enfermeiro){
        this(enfermeiro.getId(), enfermeiro.getNome(), enfermeiro.getCpf());
    }
}
