package com.Tesis.Programacion.Model.DTO.DTOResponse.Taller;

import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TallerResponse {
    private Long id;
    private EnumResponse especialidad;
    private String nombre;
    private Boolean activo;
    private Long reparacionesActivas;
    private String direccion;
    private UsuarioResponse encargadoTaller;

}
