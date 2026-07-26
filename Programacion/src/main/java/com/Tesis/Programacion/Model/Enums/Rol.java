package com.Tesis.Programacion.Model.Enums;

public enum Rol {
    ADMIN("Admin"), EMPLEADO("Empleado"), ENCARGADOTALLER("Encargado taller");

    private String label;

    Rol(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
