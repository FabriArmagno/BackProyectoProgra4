package com.Tesis.Programacion.Model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class VehiculoDetalleDTO {
    private String make;
    private String model;
    private String description;
    private int year;
    private List<BodyDTO> bodies;
    private List<EngineDTO>engines;
    private List<TransmissionDTO>transmissions;
    private List<DriveTypeDTO>drive_types;
}
