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
    private ValidacionesService validacionesService;

    // Crear un cliente verificando que no exista

    public ClienteResponse crearCliente(CrearClienteRequest request){

        validacionesService.validarDni(request.getDni());
        validacionesService.validarEmail(request.getEmail());

        Cliente cliente=new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDni(request.getDni());

        String telefonoLimpio= request.getTelefono().replaceAll("[\\s\\-\\(\\)]", "").trim();
        cliente.setTelefono(telefonoLimpio);

        cliente.setEmail(request.getEmail());
        cliente.setActivo(true);

        return ClienteMapper.toDto(clienteRepository.save(cliente));
    }

    // Listar todos los clientes

    public List<ClienteResponse>getClientes(Boolean activo, String busqueda){
        List<Cliente>clientes;

        if(activo!=null){
            clientes=clienteRepository.findByActivo(activo);
        }else{
            clientes=clienteRepository.findAll();
        }

        if(busqueda!=null && !busqueda.isBlank()){
            String[]palabras=busqueda.toLowerCase().trim().split("\\s+");

            clientes=clientes.stream().filter(c->{
                    String infoCliente=(
                                    c.getNombre() + " " +
                                    c.getApellido() + " " +
                                    c.getDni()
                                ).toLowerCase();

                    for (String palabra:palabras){
                        if(!infoCliente.contains(palabra)){
                            return false;
                        }
                    }
                    return true;

                     }
                    )
                    .toList();
        }

        return clientes.stream()
                .map(ClienteMapper::toDto)
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
            validacionesService.validarDni(request.getDni());
            cliente.setDni(request.getDni());
        }

        if(request.getEmail()!=null && !request.getEmail().equals(cliente.getEmail())){
            validacionesService.validarEmail(request.getEmail());
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

    /// -----------------------------------------------METODOS PARA MOSTRAR LOS KPIs EN EL FRONT--------------------------------------------------------

    public Long countClientes(){
        return clienteRepository.count();
    }
}
