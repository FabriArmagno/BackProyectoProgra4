package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.AsignarTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.CrearTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.UpdateTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.Enums.Especialidad;
import com.Tesis.Programacion.Model.Enums.Estado;
import com.Tesis.Programacion.Model.Enums.EstadoReparacion;
import com.Tesis.Programacion.Model.Enums.Rol;
import com.Tesis.Programacion.Model.HistorialReparacion;
import com.Tesis.Programacion.Model.Mapper.TallerMapper;
import com.Tesis.Programacion.Model.Taller;
import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Model.Vehiculo;
import com.Tesis.Programacion.Repository.TallerRepository;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import com.Tesis.Programacion.Repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TallerService {
    @Autowired
    private TallerRepository tallerRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private HistorialReparacionService historialReparacionService;

    // Crear taller

    public TallerDetalleResponse createTaller(CrearTallerRequest request){
        Usuario usuario=encontrarEncargado(request.getIdEncargadoTaller());

        Taller taller=new Taller();
        taller.setEspecialidad(request.getEspecialidad());
        taller.setNombre(request.getNombre());
        taller.setEncargadoTaller(usuario);
        taller.setDireccion(request.getDireccion());
        taller.setActivo(true);

        return TallerMapper.toDetalleDto(tallerRepository.save(taller));
    }

    // Listar todos los talleres

    public List<TallerResponse>getTalleres(){
        return mapear(tallerRepository.findAll());
    }

    // Listar todos los taller por estado

    public List<TallerResponse>getTalleresPorEstado(Boolean activo){
        if(activo==null){
            return getTalleres();
        }else{
            return mapear(tallerRepository.findByActivo(activo));
        }
    }

    // Obtener el detalle de un taller

    public TallerDetalleResponse getTallerByID(Authentication auth, Long id){
        String email=auth.getName();

        Usuario usuario=usuarioRepository.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Taller taller=encontrarTaller(id);

        if(usuario.getRol()!=Rol.ADMIN && !usuario.getId().equals(taller.getEncargadoTaller().getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Este taller pertenece a otro encargado");
        }

        if(usuario.getRol()!=Rol.ADMIN && !taller.getActivo()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Este taller esta desactivado");
        }

        return TallerMapper.toDetalleDto(taller);
    }

    // Traer los talleres del encargado taller
    public List<TallerResponse>getTalleresPorEncargado(Authentication authentication){
        Usuario usuario=usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return mapear(tallerRepository.findByEncargadoTallerIdAndActivoTrue(usuario.getId()));
    }

    // Eliminar un taller(baja logica para no eliminar el historial de reparaciones)

    public void deleteTaller(Long id){
        Taller taller=encontrarTaller(id);
        taller.setActivo(false);
        tallerRepository.save(taller);
    }

    // Activar un taller que se habia eliminado

    public void activarTaller(Long id){
        Taller taller=encontrarTaller(id);
        taller.setActivo(true);
        tallerRepository.save(taller);
    }

    // Actualizar un taller

    public TallerDetalleResponse modificarTaller(Long id, UpdateTallerRequest request){
        Taller taller=encontrarTaller(id);

        if(request.getDireccion()!=null) taller.setDireccion(request.getDireccion());
        if(request.getEspecialidad()!=null) taller.setEspecialidad(request.getEspecialidad());
        if(request.getNombre()!=null) taller.setNombre(request.getNombre());
        if(request.getIdEncargadoTaller()!=null) taller.setEncargadoTaller(encontrarEncargado(request.getIdEncargadoTaller()));

        return TallerMapper.toDetalleDto(tallerRepository.save(taller));
    }

    //Asignar el vehiculo a un taller
    @Transactional
    public void asignarVehiculoATaller(AsignarTallerRequest request){
        Vehiculo vehiculo = vehiculoRepository.findById(request.getIdVehiculo())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado "));

        Taller taller = encontrarTallerActivo(request.getIdTaller());

        HistorialReparacion historialReparacion=new HistorialReparacion();
        historialReparacion.setVehiculo(vehiculo);
        historialReparacion.setTaller(taller);
        historialReparacion.setFechaDeEntrada(LocalDate.now());
        historialReparacion.setMotivo(request.getMotivo());
        historialReparacion.setEstadoReparacion(EstadoReparacion.INGRESO);
        historialReparacionService.saveHistorialReparacion(historialReparacion);

        vehiculo.setEstado(Estado.ENREPARACION);
        vehiculoRepository.save(vehiculo);
    }

    // Obtener las especialidades

    public List<EnumResponse>obtenerEspecialidades(){
        return Arrays.stream(Especialidad.values())
                .map(especialidad -> new EnumResponse(
                        especialidad.name(),
                        especialidad.getLabel()
                ))
                .toList();
    }

    public Usuario encontrarEncargado(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El usuario no existe")
                );

        if(usuario.getRol()== Rol.ENCARGADOTALLER){
            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no tiene el rol de Encargado de Taller");
        }
    }

    public Taller encontrarTaller(Long id){
        return tallerRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El taller no existe"
                ));
    }

    public Taller encontrarTallerActivo(Long id){
        return tallerRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El taller no existe"
                ));
    }

    // Metodo para reutilizar la conversion a dto

    private List<TallerResponse>mapear(List<Taller>talleres){
        return talleres.stream()
                .map(TallerMapper::toDto)
                .toList();
    }
}
