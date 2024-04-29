package com.youx.clinica.repositorio;

import com.youx.clinica.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

}
