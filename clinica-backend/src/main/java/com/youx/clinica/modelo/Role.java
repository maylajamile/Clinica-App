package com.youx.clinica.modelo;

public enum Role {
    MEDICO("MEDICO"),
    ENFERMEIRO("ENFERMEIRO");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
