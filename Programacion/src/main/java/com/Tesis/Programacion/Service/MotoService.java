package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.DTORequest.Vehiculo.Moto.CrearMotoRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Moto.TipoMotoResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Vehiculo.Moto.MotoDetalleResponse;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Enums.TipoMoto;
import com.Tesis.Programacion.Model.Mapper.MotoMapper;
import com.Tesis.Programacion.Model.Moto;
import com.Tesis.Programacion.Repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository repo;

    @Autowired
    private UploadFileService uploadService;

    //crear moto
    public MotoDetalleResponse crearMoto(CrearMotoRequest request, List<MultipartFile>files){


        Moto moto=new Moto();
        moto.setPatente(request.getPatente());
        moto.setMarca(request.getMarca());
        moto.setModelo(request.getModelo());
        moto.setPrecio(request.getPrecio());
        moto.setColor(request.getColor());
        moto.setAnio(request.getAnio());
        moto.setKilometraje(request.getKilometraje());
        moto.setCombustion(request.getCombustion());
        moto.setMotor(request.getMotor());
        moto.setVersion(request.getVersion());
        moto.setDescripcion(request.getDescripcion());
        moto.setFechaIngreso(LocalDate.now());
        moto.setEstado(Estado.DISPONIBLE);
        moto.setTipoMoto(request.getTipoMoto());
        moto.setCilindrada(request.getCilindrada());

        //LOGICA DE IMAGENES

        if (files != null && !files.isEmpty()){
            for (MultipartFile file : files){
                if (!file.isEmpty()){
                    try {
                        String nombreImagen = uploadService.guardarImagen(file);
                        moto.getImagenes().add(nombreImagen);
                    }catch (IOException e){
                        throw new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Error al procesar las imagenes" + e.getMessage()
                        );
                    }
                }
            }
        }

        return MotoMapper.toDetalleDTO(repo.save(moto));
    }

    //Obtener los tipos de motos

    public List<TipoMotoResponse>obtenerTiposDeMotos() {
        return Arrays.stream(TipoMoto.values())
                .map(tipoMoto -> new TipoMotoResponse(
                        tipoMoto.name(),
                        tipoMoto.getLabel()
                ))
                .toList();
    }

    public MotoDetalleResponse editarMoto(Long id, CrearMotoRequest request) {
        Moto moto = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto no encontrada con ID: " + id)
        );

        List<String> imagenesActuales = moto.getImagenes();

        moto.setPatente(request.getPatente());
        moto.setMarca(request.getMarca());
        moto.setModelo(request.getModelo());
        moto.setPrecio(request.getPrecio());
        moto.setColor(request.getColor());
        moto.setAnio(request.getAnio());
        moto.setKilometraje(request.getKilometraje());
        moto.setVersion(request.getVersion());
        moto.setDescripcion(request.getDescripcion());
        moto.setTipoMoto(request.getTipoMoto());
        moto.setCilindrada(request.getCilindrada());

        moto.setImagenes(imagenesActuales);

        return MotoMapper.toDetalleDTO(repo.save(moto));
    }

}
