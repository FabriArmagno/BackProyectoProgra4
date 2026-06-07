package com.Tesis.Programacion.Model.DTO.DTOResponse.CarApi;

import lombok.Getter;

import java.util.List;

@Getter
public class VehiculoDetalleDTO {
    private String make;
    private String model;
    private String submodel;
    private String description;
    private int year;
    private List<BodyDTO> bodies;
    private List<EngineDTO>engines;
    private List<TransmissionDTO>transmissions;
    private List<DriveTypeDTO>drive_types;
}
