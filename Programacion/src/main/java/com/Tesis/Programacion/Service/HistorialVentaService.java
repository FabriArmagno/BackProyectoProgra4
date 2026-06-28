package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.*;
import com.Tesis.Programacion.Model.DTO.DTORequest.Ventas.CrearVentaRequest;
import com.Tesis.Programacion.Model.DTO.DTOResponse.Venta.VentaResponse;
import com.Tesis.Programacion.Model.Mapper.VentaMapper;
import com.Tesis.Programacion.Repository.ClienteRepository;
import com.Tesis.Programacion.Repository.HistorialVentaRepository;
import com.Tesis.Programacion.Repository.UsuarioRepository;
import com.Tesis.Programacion.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@Service
public class HistorialVentaService {

    @Autowired
    private HistorialVentaRepository historialVentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;


    //CRUD

    public VentaResponse createHistorial(Authentication authentication,CrearVentaRequest ventaRequest, Long vehiculoId) {
        Cliente cliente = clienteRepository.findById(ventaRequest.getClienteId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Usuario vendedor = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado"));

        if(ventaRequest.getPrecioVenta()<=0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio de venta debe ser mayor a 0");
        }

        HistorialVenta historialVenta=new HistorialVenta();
        historialVenta.setVehiculo(vehiculo);
        historialVenta.setCliente(cliente);
        historialVenta.setVendedor(vendedor);
        historialVenta.setPrecioVenta(ventaRequest.getPrecioVenta());
        historialVenta.setFechaVenta(LocalDate.now());

        return VentaMapper.toDto(historialVentaRepository.save(historialVenta));
    }

    public List<VentaResponse> getVentas() {
        return historialVentaRepository.findAll().stream()
                .map(VentaMapper::toDto)
                .toList();
    }

    public List<VentaResponse>getVentasPorEmpleado(Authentication authentication){
        return historialVentaRepository.findByVendedorEmail(authentication.getName())
                .stream()
                .map(VentaMapper::toDto)
                .toList();
    }


    public VentaResponse getVentaById(Long id, Authentication authentication) {
        HistorialVenta venta = historialVentaRepository.findById(id).orElse(null);
        if (venta == null) return null;

        String username = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        // Regla de negocio: Si es admin O si es el vendedor que realizó la venta, puede ver el detalle
        if (isAdmin || venta.getVendedor().getUsername().equals(username)) {
            return VentaMapper.toDto(venta);
        } else {
            throw new RuntimeException("No tenés permisos para ver esta venta");
        }
    }

    ///-----------------------------------------------CONTAR VENTAS--------------------------------------------------------

    public Long contarVentas(Authentication authentication) {
        String username = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (isAdmin) {
            return historialVentaRepository.count();
        } else {
            return historialVentaRepository.countByVendedorUsername(username);
        }
    }
}
