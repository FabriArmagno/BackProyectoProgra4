package com.Tesis.Programacion.Model.Enums;

public enum Estado {
    DISPONIBLE("Disponible"),
    RESERVADO("Reservado"),
    ENREPARACION("En reparacion"),
    VENDIDO("Vendido");

    private String label;

    Estado(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
