package com.Tesis.Programacion.Model.Enums;

public enum EstadoReparacion {
    INGRESO("Ingreso"),
    DIAGNOSTICO("Diagnostico"),
    REPARACION("Reparacion"),
    PRUEBA("Prueba"),
    FINALIZADO("Finalizado"),
    ENTREGADO("Entregado");

    private String label;

    EstadoReparacion(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
