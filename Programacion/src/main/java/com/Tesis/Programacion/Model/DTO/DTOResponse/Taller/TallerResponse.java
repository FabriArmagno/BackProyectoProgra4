package com.Tesis.Programacion.Model.DTO.DTOResponse.Taller;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.Enums.Especialidad;
import com.Tesis.Programacion.Model.Usuario;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TallerResponse {
    private Long id;
    private Especialidad especialidad;
    private String nombre;
    private Boolean activo;
    private Long reparacionesActivas;
    private String direccion;
    private UsuarioResponse encargadoTaller;

}
