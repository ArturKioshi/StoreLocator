package com.mapas.maps.controllers;

import com.mapas.maps.dtos.store.*;
import com.mapas.maps.entities.StoreEntity;
import com.mapas.maps.services.store.StoreCrudService;
import com.mapas.maps.services.store.StoreGeoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreCrudService storeCrudService;
    private final StoreGeoService storeGeoService;

    public StoreController(StoreCrudService storeCrudService, StoreGeoService storeGeoService) {
        this.storeCrudService = storeCrudService;
        this.storeGeoService = storeGeoService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateStoreResponseDTO> createStore(@RequestBody CreateStoreRequestDTO createStoreRequestDTO) {
        CreateStoreResponseDTO response = this.storeCrudService.createStore(createStoreRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteStore(@RequestBody DeleteStoreRequestDTO deleteStoreRequestDTO) {
        this.storeCrudService.deleteStore(deleteStoreRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/find-nearby-address")
    public ResponseEntity<FindStoresNearbyAddressResponseDTO> findNearbyStoresAddress(@RequestBody FindStoresNearbyAddressRequestDTO findStoresNearbyAdressRequestDTO) {
        FindStoresNearbyAddressResponseDTO response = this.storeGeoService.findStoresNearbyAdress(findStoresNearbyAdressRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/find-nearby-coordinates")
    public ResponseEntity<FindStoresNearbyCoordinatesResponseDTO> findNearbyCoordinates(@RequestBody FindStoresNearbyCoordinatesRequestDTO findStoresNearbyCoordinatesRequestDTO) {
        FindStoresNearbyCoordinatesResponseDTO response = this.storeGeoService.findStoresNearbyCoordinates(findStoresNearbyCoordinatesRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
