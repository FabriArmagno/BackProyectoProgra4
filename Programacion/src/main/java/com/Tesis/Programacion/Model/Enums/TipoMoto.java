package com.Tesis.Programacion.Model.Enums;

import lombok.Getter;

@Getter
public enum TipoMoto {
    URBANA("Urbana"),
    DEPORTIVAS("Deportiva"),
    TURISMO("Turismo"),
    OFF_ROAD("Off road"),
    ESPECIALES("Especiales");

    private String label;

    TipoMoto(String label) {
        this.label=label;
    }
}
