package com.youx.clinica.controlador;

import com.youx.clinica.dto.EnfermeiroResponseDTO;
import com.youx.clinica.dto.PacienteRequestDTO;
import com.youx.clinica.dto.PacienteResponseDTO;
import com.youx.clinica.modelo.Enfermeiro;
import com.youx.clinica.modelo.Paciente;
import com.youx.clinica.repositorio.EnfermeiroRespositorio;
import com.youx.clinica.repositorio.PacienteRepositorio;
import com.youx.clinica.util.CriptografiaUtil;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clinica")
public class ClinicaControlador {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private EnfermeiroRespositorio enfermeiroRespositorio;

    @PostMapping("/cadastrar/paciente")
    @PreAuthorize("hasAuthority('SCOPE_MEDICO') or hasAuthority('SCOPE_ENFERMEIRO')")
    public ResponseEntity<String> cadastrarPaciente(@RequestBody @Valid PacienteRequestDTO dados){
        try {
            Paciente paciente = new Paciente(dados);
            String cpfCriptografado = CriptografiaUtil.criptografarCpf(paciente.getCpf());
            paciente.setCpf(cpfCriptografado);
            pacienteRepositorio.save(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Este CPF já está em uso por outro paciente.");
        } catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().body("Todos os dados devem ser preenchidos.");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Erro inesperado: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_MEDICO') or hasAuthority('SCOPE_ENFERMEIRO')")
    @GetMapping("/listar/pacientes")
    public List<PacienteResponseDTO> listarPacientes() {
        List<PacienteResponseDTO> listaDePacientes = pacienteRepositorio.findAll().stream().map(PacienteResponseDTO::new).toList();
        return listaDePacientes;
    }

    @GetMapping("/listar/enfermeiros")
    @PreAuthorize("hasAuthority('SCOPE_MEDICO')")
    public List<EnfermeiroResponseDTO> listarEnfermeiros(){
        List<EnfermeiroResponseDTO> listaDeEnfermeiros = enfermeiroRespositorio.findAll().stream().map(EnfermeiroResponseDTO::new).toList();
        return listaDeEnfermeiros;
    }
}
