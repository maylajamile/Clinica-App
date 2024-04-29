package com.youx.clinica.dto;

import com.youx.clinica.modelo.Paciente;

import java.util.Date;

public record PacienteResponseDTO(String id, String nome, String cpf, Date dataNascimento, Double peso, Double altura, String uf) {
    public PacienteResponseDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getDataNascimento(), paciente.getPeso(), paciente.getAltura(), paciente.getUf());
    }
}
