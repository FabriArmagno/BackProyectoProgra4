package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.DTORequest.Usuario.CrearUsuarioRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Usuario.UpdateUsuarioRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Enum.EnumResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
import com.Tesis.Programacion.Model.Enums.Especialidad;
import com.Tesis.Programacion.Model.Enums.Rol;
import com.Tesis.Programacion.Model.Mapper.UsuarioMapper;
import com.Tesis.Programacion.Model.Usuario;
import com.Tesis.Programacion.Repository.ClienteRepository;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidacionesService validacionesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear un usuario verificando que no exista

    public UsuarioResponse createUser(CrearUsuarioRequest request){
       validacionesService.validarDni(request.getDni());
       validacionesService.validarEmail(request.getEmail());

        Usuario usuario=new Usuario();
        usuario.setDni(request.getDni());
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setRol(request.getRol());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);

        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    // Listar todos los usuarios

    public List<UsuarioResponse>getUsuarios(Boolean activo){
        List<Usuario>usuarios;

        if(activo!=null){
            usuarios=usuarioRepository.findByActivo(activo);
        }else{
            usuarios=usuarioRepository.findAll();
        }

        return usuarios
                .stream()
                .map(usuario -> UsuarioMapper.toDto(usuario))
                .toList();
    }

    // Dar de baja un usuario(baja logica)

    public void bajaDeUsuario(Long id){
        Usuario usuario=encontrarUsuario(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    // Activar un usuario que estaba dado de baja

    public void activarUsuario(Long id){
        Usuario usuario=encontrarUsuario(id);
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    // Actualizar un usuario

    public UsuarioResponse actualizarUsuario(UpdateUsuarioRequest request, Long idUsuario){
        Usuario usuario=encontrarUsuario(idUsuario);

        if(request.getDni()!=null && !usuario.getDni().equals(request.getDni())){
            validacionesService.validarDni(request.getDni());
            usuario.setDni(request.getDni());
        }

        if(request.getEmail()!=null && !usuario.getEmail().equals(request.getEmail())) {
            validacionesService.validarEmail(request.getEmail());
            usuario.setEmail(request.getEmail());
        }

        if(request.getNombre()!=null) usuario.setNombre(request.getNombre());
        if(request.getApellido()!=null) usuario.setApellido(request.getApellido());
        if(request.getRol()!=null) usuario.setRol(request.getRol());

        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    // Metodo para traer todos los encargados de taller
    public List<UsuarioResponse> getEncargadosDeTaller(){
        return usuarioRepository.findByRolAndActivoTrue(Rol.ENCARGADOTALLER)
                .stream()
                .map(usuario -> UsuarioMapper.toDto(usuario))
                .toList();
    }

    // Metodo para traer todos los empleado
    public List<UsuarioResponse> getEmpleados(){
        return usuarioRepository.findByRol(Rol.EMPLEADO)
                .stream()
                .map(usuario -> UsuarioMapper.toDto(usuario))
                .toList();
    }

    public List<EnumResponse>obtenerRoles(){
        return Arrays.stream(Rol.values())
                .map(rol -> new EnumResponse(
                        rol.name(),
                        rol.getLabel()
                ))
                .toList();
    }

    // Metodo para verificar si existe el usuario
    public Usuario encontrarUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

}
