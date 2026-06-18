package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.DTORequest.Usuario.CrearUsuarioRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Usuario.UpdateUsuarioRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Usuario.UsuarioResponse;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear un usuario verificando que no exista

    public UsuarioResponse createUser(CrearUsuarioRequest request){
       validarDni(request.getDni());
       validarEmail(request.getEmail());

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

    public List<UsuarioResponse>getUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> UsuarioMapper.toDto(usuario))
                .toList();
    }

    public List<UsuarioResponse>getUsuariosPorEstado(Boolean activo){
        if(activo==null){
            return getUsuarios();
        }else{
            return usuarioRepository.findByActivo(activo)
                    .stream()
                    .map(usuario -> UsuarioMapper.toDto(usuario))
                    .toList();
        }
    }

    // Dar de baja un usuario(baja logica)

    public UsuarioResponse bajaDeUsuario(Long id){
        Usuario usuario=encontrarUsuario(id);
        usuario.setActivo(false);

        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    // Activar un usuario que estaba dado de baja

    public UsuarioResponse activarUsuario(Long id){
        Usuario usuario=encontrarUsuario(id);
        usuario.setActivo(true);

        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    // Actualizar un usuario

    public UsuarioResponse actualizarUsuario(UpdateUsuarioRequest request, Long idUsuario){
        Usuario usuario=encontrarUsuario(idUsuario);

        if(request.getDni()!=null && !usuario.getDni().equals(request.getDni())){
            validarDni(request.getDni());
            usuario.setDni(request.getDni());
        }

        if(request.getEmail()!=null && !usuario.getEmail().equals(request.getEmail())) {
            validarEmail(request.getEmail());
            usuario.setEmail(request.getEmail());
        }

        if(request.getNombre()!=null) usuario.setNombre(request.getNombre());
        if(request.getApellido()!=null) usuario.setApellido(request.getApellido());
        if(request.getRol()!=null) usuario.setRol(request.getRol());

        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    // Metodo para verificar si existe el usuario
    public Usuario encontrarUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    public Usuario encontrarUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    // Metodo para validar que el DNI no exista
    public void validarDni(Integer dni){
        if (usuarioRepository.existsByDni(dni) || clienteRepository.existsByDni(dni)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El DNI ya esta registrado");
        }
    }

    // Metodo para validar que el email no exista
    public void validarEmail(String email){
        if (usuarioRepository.existsByEmail(email) || clienteRepository.existsByEmail(email)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya esta registrado");
        }
    }

    // Metodo para traer todos los encargados de taller
    public List<UsuarioResponse> getEncargadosDeTaller(){
        Rol rol = Rol.ENCARGADOTALLER;

        return usuarioRepository.findByRolAndActivoTrue(rol)
                .stream()
                .map(usuario -> UsuarioMapper.toDto(usuario))
                .toList();
    }
}
