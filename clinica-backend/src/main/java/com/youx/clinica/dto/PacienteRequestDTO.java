package com.youx.clinica.dto;

import java.util.Date;

public record PacienteRequestDTO(String id, String nome, String cpf, Date dataNascimento, Double peso, Double altura, String uf){

}
