package com.Tesis.Programacion.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CarApiService {

    private final WebClient webClient;

    public CarApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<String> obtenerMarcas(String tipoVehiculo){

        String endpoint=tipoVehiculo.equals("MOTO")
                ? "/api/makes/powersports"
                : "/api/makes/v2";

        return webClient.get()//Hace una peticion HTTP GET
                .uri(endpoint)//Define el endpoint
                .retrieve()//Ejecuta la peticion
                .bodyToFlux(String.class)//Devuelve un flux(una lista de strings)
                .collectList()//convierte el flux en una lista normal
                .block();//Lo vuelve sincronico
    }

    public List<String> obtenerModelos(String tipo, String marca){

        String endpoint=tipo.equals("MOTO")
                ? "/api/models/powersports"
                : "/api/models/v2";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("make", marca)//Esto lo que hace es agregar un parametro en la ruta llamado make y le asigna el valor de marca
                        .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block();
    }

    public List<Integer> obtenerAnios(String tipo, String modelo){

        String endpoint=tipo.equals("MOTO")
                ? "/api/years/powersports"
                : "/api/years/v2";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("model",modelo)
                        .build()
                )
                .retrieve()
                .bodyToFlux(Integer.class)
                .collectList()
                .block();
    }
}
