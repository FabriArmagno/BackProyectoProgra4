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

    public VentaResponse createHistorial(Authentication authentication, CrearVentaRequest ventaRequest, Long vehiculoId) {
        Cliente cliente = clienteRepository.findById(ventaRequest.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Usuario vendedor = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado"));

        if (ventaRequest.getPrecioVenta() <= vehiculo.getPrecioCompra()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio de venta debe ser mayor a el precio de compra");
        }

        HistorialVenta historialVenta = new HistorialVenta();
        historialVenta.setVehiculo(vehiculo);
        historialVenta.setCliente(cliente);
        historialVenta.setVendedor(vendedor);
        historialVenta.setPrecioCompra(vehiculo.getPrecioCompra());
        historialVenta.setPrecioFinalVenta(ventaRequest.getPrecioVenta());
        historialVenta.setFechaVenta(LocalDate.now());

        return VentaMapper.toDto(historialVentaRepository.save(historialVenta));
    }

    public List<VentaResponse> getVentas(Long empleadoId, LocalDate desde, LocalDate hasta) {
        List<HistorialVenta> ventas;

        // Evaluamos si nos mandaron el rango de fechas completo
        boolean tieneFechas = desde != null && hasta != null;

        if (empleadoId != null && tieneFechas) {
            // Tiene AMBOS filtros
            ventas = historialVentaRepository.findByVendedorIdAndFechaVentaBetween(empleadoId, desde, hasta);

        } else if (empleadoId != null) {
            // Solo filtro de empleado
            ventas = historialVentaRepository.findByVendedorId(empleadoId);

        } else if (tieneFechas) {
            // Solo filtro de fechas
            ventas = historialVentaRepository.findByFechaVentaBetween(desde, hasta);

        } else {
            // No mandaron filtros, devolvemos todo
            ventas = historialVentaRepository.findAll();
        }

        return ventas.stream()
                .map(VentaMapper::toDto)
                .toList();
    }

    //Metodo para que cada empleado vea sus propias ventas
    public List<VentaResponse> getVentasPorEmpleado(Authentication authentication) {
        return historialVentaRepository.findByVendedorEmail(authentication.getName())
                .stream()
                .map(VentaMapper::toDto)
                .toList();
    }

    /// -----------------------------------------------CONTAR VENTAS--------------------------------------------------------

    public Long contarVentas() {
        return historialVentaRepository.count();
    }

}
