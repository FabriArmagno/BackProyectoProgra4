package com.Tesis.Programacion.Model.Enums;

public enum Rol {
    ADMIN("Admin"), EMPLEADO("Empleado"), ENCARGADOTALLER("Encargado taller"), CLIENTE("Cliente");

    private String label;

    Rol(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
