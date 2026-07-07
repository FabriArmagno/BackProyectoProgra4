package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.*;
import com.Tesis.Programacion.Model.DTO.DTORequest.Ventas.CrearVentaRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.VehiculoResponse;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Mapper.AutoMapper;
import com.Tesis.Programacion.Model.Mapper.MotoMapper;
import com.Tesis.Programacion.Model.Mapper.VehiculoMapper;
import com.Tesis.Programacion.Repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UploadFileService uploadService;

    @Autowired
    private HistorialVentaService historialVentaService;

    public List<VehiculoResponse>getVehiculos(Estado estado, String busqueda){
        List<Vehiculo>vehiculos;

        if(estado!=null){
            vehiculos=vehiculoRepository.findByEstado(estado);
        }else{
            vehiculos=vehiculoRepository.findAll();
        }

        if(busqueda!=null && !busqueda.isBlank()){
            String b = busqueda.toLowerCase();

            vehiculos=vehiculos.stream().filter(v->
                                v.getMarca().toLowerCase().contains(b) ||
                                v.getModelo().toLowerCase().contains(b) ||
                                v.getVersion().toLowerCase().contains(b) ||
                                v.getPatente().toLowerCase().contains(b) ||
                                String.valueOf(v.getAnio()).contains(b)
                    ).toList();
        }

       return vehiculos
               .stream()
               .map(VehiculoMapper::toDto)
               .toList();
    }

    // Mostrar el detalle de un auto con el ID

    public VehiculoDetalleResponse getVehiculoById(Long id){
        Vehiculo vehiculo = encontrarVehiculo(id);

        if(vehiculo instanceof Auto auto){
            return AutoMapper.toDetalleDTO(auto);
        }else if(vehiculo instanceof Moto moto){
            return MotoMapper.toDetalleDTO(moto);
        }

        throw new IllegalStateException("Tipo de vehiculo desconocido");
    }

    // Eliminar vehiculo

    public void eliminarVehiculo(Long id){
        Vehiculo vehiculo=encontrarVehiculo(id);

        if(!vehiculo.getHistorial().isEmpty()){
            throw new RuntimeException("No se puede eliminar un vehiculo con historial");
        }

        vehiculoRepository.deleteById(id);
    }

    // Vender el vehiculo
    @Transactional
    public void venderAuto(Authentication authentication, CrearVentaRequest request, Long vehiculoId){
        Vehiculo vehiculo = encontrarVehiculo(vehiculoId);

        if(vehiculo.getEstado()==Estado.VENDIDO){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El vehiculo ya esta vendido");
        }

        vehiculo.setEstado(Estado.VENDIDO);
        vehiculoRepository.save(vehiculo);

        historialVentaService.createHistorial(authentication, request, vehiculoId);
    }

    // METODO PARA ENCONTRAR EL VEHICULO

    private Vehiculo encontrarVehiculo(Long id){
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado"));
    }

    ///------------------------------------------IMAGENES---------------------------------------------------------------


    // --- AGREGAR MÁS IMÁGENES A UN VEHÍCULO EXISTENTE ---
    public void agregarImagenes(Long id, List<MultipartFile> files) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado"));

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        String nombreImagen = uploadService.guardarImagen(file);
                        vehiculo.getImagenes().add(nombreImagen); // Se acopla a la lista existente
                    } catch (IOException e) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar la imagen");
                    }
                }
            }
            vehiculoRepository.save(vehiculo); // Guarda los nuevos registros en la tabla intermedia
        }
    }

    public void eliminarImagen(Long id, String nombreImagen) {
        Vehiculo vehiculo = encontrarVehiculo(id);

        // 1. Verificamos si el vehículo realmente tiene esa imagen asociada
        if (!vehiculo.getImagenes().contains(nombreImagen)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen no pertenece a este vehículo");
        }

        // 2. se elimina el registro de la base de datos
        vehiculo.getImagenes().remove(nombreImagen);
        vehiculoRepository.save(vehiculo);

        // 3. se elimina el archivo fisico
        uploadService.eliminarArchivo(nombreImagen);

    }

    ///----------------------------------------------ESTADO--------------------------------------------------------------

    //Obtener estados
    public List<EnumResponse>getEstados(){
        return Arrays.stream(Estado.values()).
                map(estado -> new EnumResponse(
                        estado.name(),
                        estado.getLabel()
                )).toList();
    }

    ///------------------------------------------VALIDAR SI EXISTE LA PATENTE--------------------------------------------

    //Metodo para validar si la patente existe(se usa para validar en el front)
    public Boolean existePatente(String patente){
        String patenteAbuscar=patente!=null ? patente.replaceAll("\\s+", "") : "";
        return vehiculoRepository.existsByPatenteIgnoreCase(patenteAbuscar);
    }

    //Metodo para validar que la patente no exista
    public void validarPatente(String patente){
        String patenteABuscar=patente!=null ? patente.replaceAll("\\s+", "").toUpperCase() : "";
        if(vehiculoRepository.existsByPatenteIgnoreCase(patenteABuscar)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"La patente ya esta registrada");
        }
    }

}
