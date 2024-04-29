package com.youx.clinica;

import com.youx.clinica.controlador.ClinicaControlador;
import com.youx.clinica.dto.PacienteRequestDTO;
import com.youx.clinica.modelo.Paciente;
import com.youx.clinica.repositorio.PacienteRepositorio;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClinicaControladorTests {

	@Mock
	private PacienteRepositorio pacienteRepositorio;

	@InjectMocks
	private ClinicaControlador clinicaControlador;

	@Test
	public void testCadastrarPacienteSuccesso() {
		PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
				"id", "Nome", "12345678901", new Date(), 70.0, 1.75, "UF");

		ResponseEntity<String> response = clinicaControlador.cadastrarPaciente(pacienteRequestDTO);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Paciente cadastrado com sucesso.", response.getBody());
		verify(pacienteRepositorio, times(1)).save(any(Paciente.class));
	}

	@Test
	public void testCadastrarPacienteCpfDuplicado() {
		// Arrange
		PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
				"id", "Nome", "12345678901", new Date(), 70.0, 1.75, "UF");
		when(pacienteRepositorio.save(any(Paciente.class))).thenThrow(DataIntegrityViolationException.class);

		ResponseEntity<String> response = clinicaControlador.cadastrarPaciente(pacienteRequestDTO);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Este CPF já está em uso por outro paciente.", response.getBody());
	}

	@Test
	public void testCadastrarPacienteDadosInvalidos() {
		PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
				"id", "", "12345678901", new Date(), 70.0, 1.75, "UF");
		when(pacienteRepositorio.save(any(Paciente.class))).thenThrow(ConstraintViolationException.class);

		ResponseEntity<String> response = clinicaControlador.cadastrarPaciente(pacienteRequestDTO);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Todos os dados devem ser preenchidos.", response.getBody());
	}

	@Test
	public void testCadastrarPacienteUnexpectedError() {
		PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
				"id", "Nome", "12345678901", new Date(), 70.0, 1.75, "UF");
		when(pacienteRepositorio.save(any(Paciente.class))).thenThrow(RuntimeException.class);

		ResponseEntity<String> response = clinicaControlador.cadastrarPaciente(pacienteRequestDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Erro inesperado: null", response.getBody());
	}
}
