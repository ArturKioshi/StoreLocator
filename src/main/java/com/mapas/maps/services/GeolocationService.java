package com.mapas.maps.services;

import com.mapas.maps.dtos.nominatim.NominatimResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GeolocationService {

    private final WebClient webClient;

    public GeolocationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://nominatim.openstreetmap.org")
                .build();
    }


    public List<NominatimResponseDTO>  searchByAdress(String adress) {
        String url = UriComponentsBuilder.fromPath("/search")
                .queryParam("q", adress)
                .queryParam("format", "json")
                .queryParam("limit", "5")
                .build()
                .toUriString();

        return webClient.get()
                .uri(url)
                .header("User-Agent", "SpringBoot-GeolocationApp")
                .retrieve()
                .bodyToFlux(NominatimResponseDTO.class)
                .collectList()
                .block();
    }

    public NominatimResponseDTO searchByCoordanates(double lat, double lng) {
        String url = UriComponentsBuilder.fromPath("/search")
                .queryParam("lat", lat)
                .queryParam("lon", lng)
                .queryParam("format", "json")
                .build()
                .toUriString();

        return webClient.get()
                .uri(url)
                .header("User-Agent", "SpringBoot-GeolocationApp")
                .retrieve()
                .bodyToMono(NominatimResponseDTO.class)
                .block();

    }

}