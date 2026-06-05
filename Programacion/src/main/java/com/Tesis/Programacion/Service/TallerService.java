package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.CrearTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Taller.UpdateTallerRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Taller.TallerResponse;
import com.Tesis.Programacion.Model.Enums.Rol;
import com.Tesis.Programacion.Model.Mapper.TallerMapper;
import com.Tesis.Programacion.Model.Taller;
import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Repository.TallerRepository;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import io.netty.handler.codec.http2.Http2PushPromiseFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TallerService {
    @Autowired
    private TallerRepository tallerRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear taller

    public TallerDetalleResponse createTaller(CrearTallerRequest request){
        Usuario usuario=encontrarEncargado(request.getIdEncargadoTaller());

        Taller taller=new Taller();
        taller.setEspecialidad(request.getEspecialidad());
        taller.setNombre(request.getNombre());
        taller.setEncargadoTaller(usuario);
        taller.setDireccion(request.getDireccion());
        taller.setActivo(true);

        return TallerMapper.toDetalleDto(taller);
    }

    // Listar todos los talleres

    public List<TallerResponse>getTalleres(){
        return tallerRepository.findAll()
                .stream()
                .map(taller -> TallerMapper.toDto(taller))
                .toList();
    }

    // Listar todos los taller por estado

    public List<TallerResponse>getTalleresPorEstado(Boolean activo){
        return tallerRepository.findByActivo(activo)
                .stream()
                .map(taller -> TallerMapper.toDto(taller))
                .toList();
    }

    // Obtener el detalle de un taller

    public TallerDetalleResponse getTallerByID(Long id){
        return TallerMapper.toDetalleDto(encontrarTaller(id));
    }

    // Eliminar un taller(baja logica para no eliminar el historial de reparaciones)

    public TallerDetalleResponse deleteTaller(Long id){
        Taller taller=encontrarTaller(id);
        taller.setActivo(false);

        return TallerMapper.toDetalleDto(tallerRepository.save(taller));
    }

    // Activar un taller que se habia eliminado

    public TallerDetalleResponse activarTaller(Long id){
        Taller taller=encontrarTaller(id);
        taller.setActivo(true);

        return TallerMapper.toDetalleDto(tallerRepository.save(taller));
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
}
