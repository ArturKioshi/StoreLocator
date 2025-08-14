package com.mapas.maps.services.store;

import com.mapas.maps.dtos.store.FindStoresNearbyAddressRequestDTO;
import com.mapas.maps.dtos.store.FindStoresNearbyAddressResponseDTO;
import com.mapas.maps.dtos.store.FindStoresNearbyCoordinatesRequestDTO;
import com.mapas.maps.dtos.store.FindStoresNearbyCoordinatesResponseDTO;
import com.mapas.maps.entities.StoreEntity;
import com.mapas.maps.repositories.StoreRepository;
import com.mapas.maps.services.GeolocationService;
import com.mapas.maps.utils.CalculateDistance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGeoService {

    private final StoreRepository storeRepository;
    private final CalculateDistance calculateDistance;
    private final GeolocationService geolocationService;

    public StoreGeoService(StoreRepository storeRepository, CalculateDistance calculateDistance, GeolocationService geolocationService) {
        this.storeRepository = storeRepository;
        this.calculateDistance = calculateDistance;
        this.geolocationService = geolocationService;
    }

    public FindStoresNearbyAddressResponseDTO findStoresNearbyAdress(FindStoresNearbyAddressRequestDTO findStoresNearbyAddressRequestDTO) {
        var locations = geolocationService.searchByAdress(findStoresNearbyAddressRequestDTO.getAddress());

        if (locations.isEmpty() || locations == null) {
            throw new RuntimeException("No locations found");
        }

        double lat = Double.parseDouble(locations.get(0).getLat());
        double lon = Double.parseDouble(locations.get(0).getLon());

        List<StoreEntity> stores = this.storeRepository.findStoresNearby(lat, lon, findStoresNearbyAddressRequestDTO.getRadiusKm());

        return new FindStoresNearbyAddressResponseDTO(stores);
    }

    public FindStoresNearbyCoordinatesResponseDTO findStoresNearbyCoordinates(FindStoresNearbyCoordinatesRequestDTO findStoresNearbyCoordinatesRequestDTO) {
        List<StoreEntity> stores = this.storeRepository.findStoresNearby(findStoresNearbyCoordinatesRequestDTO.getLat(), findStoresNearbyCoordinatesRequestDTO.getLon(), findStoresNearbyCoordinatesRequestDTO.getRadiusKm());

        return new FindStoresNearbyCoordinatesResponseDTO(stores);
    }
}
