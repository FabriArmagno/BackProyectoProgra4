package com.Tesis.Programacion.Model.Enums;

import lombok.Getter;

@Getter
public enum Especialidad {
    general("General"),
    chapa_y_pintura("Chapa y pintura"),
    motorista("Mecanico"),
    electricsta("Electricista");

    private String label;

    Especialidad(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
