package com.youx.clinica.modelo;

import com.youx.clinica.dto.PacienteRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name="pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String nome;
    @NotBlank
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    private Double peso;
    private Double altura;
    @NotBlank
    private String uf;

    public Paciente () {}

    public Paciente(PacienteRequestDTO dados){
        this.setId(dados.id());
        this.setNome(dados.nome());
        this.setCpf(dados.cpf());
        this.setDataNascimento(dados.dataNascimento());
        this.setPeso(dados.peso());
        this.setAltura(dados.altura());
        this.setUf(dados.uf());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
