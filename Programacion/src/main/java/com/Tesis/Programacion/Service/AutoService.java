package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Auto;
import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Auto.CrearAutoRequest;
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
    private VehiculoService vehiculoService;

    @Autowired
    private UploadFileService uploadService;
    // Crear un auto

    public AutoDetalleResponse createAuto(CrearAutoRequest request, List<MultipartFile> files){
        VehiculoDetalleDTO vehiculoDetalleDTO=carApiService.obtenerDetalleDelVehiculo(request.getIdTrim());

        String patente=request.getPatente()!=null ? request.getPatente().replaceAll("\\s+", "").toUpperCase() : null;

        vehiculoService.validarPatente(patente);

        if(request.getPrecioCompra()>=request.getPrecioVenta()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio de venta debe ser mayor al precio de compra");
        }

        Auto auto=new Auto();

        auto.setPatente(patente);
        auto.setMarca(vehiculoDetalleDTO.getMake());
        auto.setModelo(vehiculoDetalleDTO.getModel());
        auto.setPrecioCompra(request.getPrecioCompra());
        auto.setPrecioVenta(request.getPrecioVenta());
        auto.setColor(request.getColor());
        auto.setAnio(vehiculoDetalleDTO.getYear());
        auto.setKilometraje(request.getKilometraje());
        auto.setVersion(vehiculoDetalleDTO.getDescription());
        auto.setDescripcion(request.getDescripcion());
        auto.setFechaIngreso(LocalDate.now());
        auto.setEstado(Estado.DISPONIBLE);
        auto.setIdTrim(request.getIdTrim());

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

    // Modificar auto por id

   public AutoDetalleResponse modificarAuto(Long id, List<MultipartFile> files, CrearAutoRequest request) {
       Auto auto = autoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Auto no encontrado"));

       String patente=request.getPatente()!=null ? request.getPatente().replaceAll("\\s+", "").toUpperCase() : null;

       if(!patente.equals(auto.getPatente())){
           vehiculoService.validarPatente(patente);
       }

       if(request.getPrecioCompra()>=request.getPrecioVenta()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio de venta debe ser mayor al precio de compra");
       }

       if (request.getIdTrim() != null) {
           auto.setIdTrim(request.getIdTrim());

           VehiculoDetalleDTO vehiculoDetalleDTO = carApiService
                   .obtenerDetalleDelVehiculo(request.getIdTrim());

           auto.setMarca(vehiculoDetalleDTO.getMake());
           auto.setModelo(vehiculoDetalleDTO.getModel());
           auto.setAnio(vehiculoDetalleDTO.getYear());
           auto.setVersion(vehiculoDetalleDTO.getDescription());
           auto.setDescripcion(request.getDescripcion());

           if (!vehiculoDetalleDTO.getEngines().isEmpty()) {
               auto.setMotor(vehiculoDetalleDTO.getEngines().getFirst().getSize());
               auto.setCombustion(vehiculoDetalleDTO.getEngines().getFirst().getEngine_type());
               auto.setPotencia(vehiculoDetalleDTO.getEngines().getFirst().getHorsepower_hp());
           }

           if (!vehiculoDetalleDTO.getDrive_types().isEmpty()) {
               auto.setTipoDeTraccion(vehiculoDetalleDTO.getDrive_types().getFirst().getDescription());
           }

           if (!vehiculoDetalleDTO.getTransmissions().isEmpty()) {
               auto.setTransmision(vehiculoDetalleDTO.getTransmissions().getFirst().getDescription());
           }

           if (!vehiculoDetalleDTO.getBodies().isEmpty()) {
               auto.setPuertas(vehiculoDetalleDTO.getBodies().getFirst().getDoors());
               auto.setTipoAuto(vehiculoDetalleDTO.getBodies().getFirst().getType());
           }
       }

       if (request.getPatente() != null) auto.setPatente(patente);
       if (request.getPrecioCompra() != null) auto.setPrecioCompra(request.getPrecioCompra());
       if (request.getPrecioVenta() != null) auto.setPrecioVenta(request.getPrecioVenta());
       if (request.getColor() != null) auto.setColor(request.getColor());
       if (request.getKilometraje() != null) auto.setKilometraje(request.getKilometraje());



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

}
