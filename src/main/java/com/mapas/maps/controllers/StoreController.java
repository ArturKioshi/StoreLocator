package com.mapas.maps.controllers;

import com.mapas.maps.dtos.store.*;
import com.mapas.maps.entities.StoreEntity;
import com.mapas.maps.services.store.StoreCrudService;
import com.mapas.maps.services.store.StoreGeoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<StoreEntity>> findNearbyStoresAddress(@RequestBody FindStoresNearbyAddressRequestDTO findStoresNearbyAdressRequestDTO) {
        List<StoreEntity> response = this.storeGeoService.findStoresNearbyAdress(findStoresNearbyAdressRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/find-nearby-coordinates")
    public ResponseEntity<List<StoreEntity>> findNearbyCoordinates(@RequestBody FindStoresNearbyCoordinatesRequestDTO findStoresNearbyCoordinatesRequestDTO) {
        List<StoreEntity> response = this.storeGeoService.findStoresNearbyCoordinates(findStoresNearbyCoordinatesRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
