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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
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

    public VentaResponse createHistorial(CrearVentaRequest ventaRequest) {
        Cliente cId = clienteRepository.findById(ventaRequest.getClienteId()).orElse(null);
        Usuario uId = usuarioRepository.findById(ventaRequest.getVendedorId()).orElse(null);
        Vehiculo vId = vehiculoRepository.findById(ventaRequest.getVehiculoId()).orElse(null);

        if (cId != null && uId != null && vId != null && ventaRequest.getPrecioVenta() > 0) {

            HistorialVenta historialVenta=new HistorialVenta();
            historialVenta.setVehiculo(vId);
            historialVenta.setCliente(cId);
            historialVenta.setVendedor(uId);
            historialVenta.setPrecioVenta(ventaRequest.getPrecioVenta());
            historialVenta.setFechaVenta(LocalDate.now());

            return VentaMapper.toDto(historialVentaRepository.save(historialVenta));
        } else {
            throw new RuntimeException("IDs o datos inválidos para registrar la venta");
        }
    }

    public List<VentaResponse> getVentas(Authentication authentication) {
        String username = authentication.getName();

        // Verificamos si el usuario tiene el rol de ADMIN
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN")); // Cambia "ROLE_ADMIN" por cómo nombres tu rol

        if (isAdmin) {
            // El Admin ve absolutamente todo
            return historialVentaRepository.findAll().stream()
                    .map(VentaMapper::toDto)
                    .toList();
        } else {
            // El empleado común solo ve sus propias ventas
            return historialVentaRepository.findByVendedorUsername(username).stream()
                    .map(VentaMapper::toDto)
                    .toList();
        }
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
