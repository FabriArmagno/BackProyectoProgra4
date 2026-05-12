package com.Tesis.Programacion.Service;

import com.Tesis.Programacion.Model.DTO.MakeResponseDTO;
import com.Tesis.Programacion.Model.DTO.ModelResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

        String endpoint=tipoVehiculo.trim().equalsIgnoreCase("MOTO")
                ? "/api/makes/powersports?type=street_motorcycle"
                : "/api/makes/v2";

        MakeResponseDTO makeResponseDTO=webClient.get()//Hace una peticion HTTP GET
                .uri(endpoint)//Define el endpoint
                .retrieve()//Ejecuta la peticion
                .bodyToMono(MakeResponseDTO.class)
                .block();//Lo vuelve sincronico

        return makeResponseDTO
                .getData()
                .stream()
                .map(makeDTO -> makeDTO.getName())
                .toList();
    }

    public List<String> obtenerModelos(String tipo, String marca){

        boolean esMoto=tipo.trim().equalsIgnoreCase("MOTO");

        String endpoint=esMoto
                ? "/api/models/powersports"
                : "/api/models/v2";

        ModelResponseDTO modelResponseDTO=webClient.get()
                .uri(uriBuilder ->
                            {
                                uriBuilder.path(endpoint)
                                .queryParam("make", marca);//Esto lo que hace es agregar un parametro en la ruta llamado make y le asigna el valor de marca

                                if (esMoto){
                                    uriBuilder.queryParam("type", "street_motorcycle");
                                }

                                return uriBuilder.build();
                            }
                )
                .retrieve()
                .bodyToMono(ModelResponseDTO.class)
                .block();

        return modelResponseDTO
                .getData()
                .stream()
                .map(modelDTO -> modelDTO.getName())
                .toList();
    }

    public List<Integer> obtenerAnios(String tipo, String modelo){

        boolean esMoto=tipo.trim().equalsIgnoreCase("MOTO");

        String endpoint=esMoto
                ? "/api/years/powersports"
                : "/api/years/v2";


        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(endpoint).queryParam("model", modelo);

                    if(esMoto){
                        uriBuilder.queryParam("type", "street_motorcycle");
                    }

                    return uriBuilder.build();
                }).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Integer>>() {})//Es para decirle a spring que la respuesta es una lista de integer
                .block();
    }
}
