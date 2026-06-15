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

    public List<VentaResponse> getVentas(){
        return historialVentaRepository.findAll().stream()
                .map(VentaMapper::toDto)
                .toList();
    }

    public VentaResponse getVentaById(Long id){
        return historialVentaRepository.findById(id)
                .map(VentaMapper::toDto)
                .orElse(null);
    }









}
