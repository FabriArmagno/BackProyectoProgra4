package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto.CrearAutoRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto.UpdateAutoRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Auto.AutoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.CarApi.VehiculoDetalleDTO;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Mapper.AutoMapper;
import com.Tesis.Programacion.Repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private CarApiService carApiService;

    @Autowired
    private UploadFileService uploadService;
    // Crear un auto

    public AutoDetalleResponse createAuto(CrearAutoRequest request, List<MultipartFile> files){
        VehiculoDetalleDTO vehiculoDetalleDTO=carApiService.obtenerDetalleDelVehiculo(request.getIdTrim());

        Auto auto=new Auto();
        auto.setPatente(request.getPatente());
        auto.setMarca(vehiculoDetalleDTO.getMake());
        auto.setModelo(vehiculoDetalleDTO.getModel());
        auto.setPrecio(request.getPrecio());
        auto.setColor(request.getColor());
        auto.setAnio(vehiculoDetalleDTO.getYear());
        auto.setKilometraje(request.getKilometraje());
        auto.setVersion(vehiculoDetalleDTO.getSubmodel());
        auto.setDescripcion(vehiculoDetalleDTO.getDescription());
        auto.setFechaIngreso(LocalDate.now());
        auto.setEstado(Estado.DISPONIBLE);

        if(!vehiculoDetalleDTO.getEngines().isEmpty()){
            auto.setMotor(vehiculoDetalleDTO.getEngines().getFirst().getSize());
            auto.setCombustion(vehiculoDetalleDTO.getEngines().getFirst().getEngine_type());
            auto.setPotencia(vehiculoDetalleDTO.getEngines().getFirst().getHorsepower_hp());
        }

        if(!vehiculoDetalleDTO.getDrive_types().isEmpty()){
            auto.setTipoDeTraccion(vehiculoDetalleDTO.getDrive_types().getFirst().getDescription());
        }

        if(!vehiculoDetalleDTO.getTransmissions().isEmpty()){
            auto.setTransmision(vehiculoDetalleDTO.getTransmissions().getFirst().getDescription());
        }

        if(!vehiculoDetalleDTO.getBodies().isEmpty()){
            auto.setPuertas(vehiculoDetalleDTO.getBodies().getFirst().getDoors());
            auto.setTipoAuto(vehiculoDetalleDTO.getBodies().getFirst().getType());
        }

        //LOGICA DE IMAGENES

        if (files != null && !files.isEmpty()){
            for (MultipartFile file : files){
                if (!file.isEmpty()){
                    try {
                        String nombreImagen = uploadService.guardarImagen(file);
                        auto.getImagenes().add(nombreImagen);
                    }catch (IOException e){
                        throw new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Error al procesar las imagenes" + e.getMessage()
                        );
                    }
                }
            }
        }

        return AutoMapper.toDetalleDTO(autoRepository.save(auto));
    }

    // Modificar auto por id (preguntar a tribu como le gusta je)

   /* ///PENDIENTE. PENSAR COMO ACTUALIZAR EL AUTO SEGUN LOS DATOS DE LA API
   public AutoDetalleResponse modificarAuto(Long id, UpdateAutoRequest request) {
       Auto auto = autoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
   }*/
}
