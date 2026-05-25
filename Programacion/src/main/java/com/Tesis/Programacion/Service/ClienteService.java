package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.Cliente;
import com.Tesis.Programacion.Model.DTO.DTORequest.Cliente.CrearClienteRequest;
import com.Tesis.Programacion.Model.DTO.DTORequest.Cliente.UpdateClienteRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteDetalleResponse;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Cliente.ClienteResponse;
import com.Tesis.Programacion.Model.Mapper.ClienteMapper;
import com.Tesis.Programacion.Repository.ClienteRepository;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un cliente verificando que no exista

    public ClienteDetalleResponse crearCliente(CrearClienteRequest request){

        validarDni(request.getDni());
        validarEmail(request.getEmail());

        Cliente cliente=new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setActivo(true);

        return ClienteMapper.toDetalleDto(clienteRepository.save(cliente));
    }

    // Listar todos los clientes

    public List<ClienteResponse>getClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> ClienteMapper.toDto(cliente))
                .toList();
    }

    // Listar todos los clientes segun el estado(si esta activo o no)

    public List<ClienteResponse>getClientesPorEstado(Boolean activo){
        return clienteRepository.findByActivo(activo)
                .stream()
                .map(cliente -> ClienteMapper.toDto(cliente))
                .toList();
    }

    // Obtener el detalle de un cliente

    public ClienteDetalleResponse getClienteById(Long id){
        Cliente cliente=encontrarCliente(id);

        return ClienteMapper.toDetalleDto(cliente);
    }

    // Actualiza un cliente

    public ClienteDetalleResponse actualizarCliente(UpdateClienteRequest request, Long id){
        Cliente cliente=encontrarCliente(id);

        if(request.getNombre()!=null) cliente.setNombre(request.getNombre());
        if(request.getApellido()!=null) cliente.setApellido(request.getApellido());

        if(request.getDni()!=null && !request.getDni().equals(cliente.getDni())){
            validarDni(request.getDni());
            cliente.setDni(request.getDni());
        }

        if(request.getEmail()!=null && !request.getEmail().equals(cliente.getEmail())){
            validarEmail(request.getEmail());
            cliente.setEmail(request.getEmail());
        }

        if(request.getTelefono()!=null) cliente.setTelefono(request.getTelefono());

        return ClienteMapper.toDetalleDto(clienteRepository.save(cliente));
    }

    // Eliminar un cliente(baja logica)

    public ClienteDetalleResponse bajaDeCliente(Long id){
        Cliente cliente=encontrarCliente(id);
        cliente.setActivo(false);

        return ClienteMapper.toDetalleDto(clienteRepository.save(cliente));
    }

    // Activar un usuario que se habia eliminado

    public ClienteDetalleResponse activarCliente(Long id){
        Cliente cliente=encontrarCliente(id);
        cliente.setActivo(true);

        return ClienteMapper.toDetalleDto(clienteRepository.save(cliente));
    }

    // Metodo para encontrar el cliente
    public Cliente encontrarCliente(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"));
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

}
