package com.mapas.maps.services.store;

import com.mapas.maps.dtos.store.CreateStoreRequestDTO;
import com.mapas.maps.dtos.store.CreateStoreResponseDTO;
import com.mapas.maps.dtos.store.DeleteStoreRequestDTO;
import com.mapas.maps.entities.StoreEntity;
import com.mapas.maps.repositories.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreCrudService {

    private final StoreRepository storeRepository;

    public StoreCrudService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public CreateStoreResponseDTO createStore(CreateStoreRequestDTO createStoreRequestDTO) {
        this.storeRepository.findByAddress(createStoreRequestDTO.getAddress())
                .ifPresent(store -> {
                    throw new RuntimeException("Store already exists");
                });

        StoreEntity storeEntity = StoreEntity.builder()
                .name(createStoreRequestDTO.getName())
                .address(createStoreRequestDTO.getAddress())
                .latitude(createStoreRequestDTO.getLatitude())
                .longitude(createStoreRequestDTO.getLongitude())
                .category(createStoreRequestDTO.getCategory())
                .build();

        StoreEntity storeSaved = storeRepository.save(storeEntity);

        return new CreateStoreResponseDTO(
                storeEntity.getId(),
                storeEntity.getName(),
                storeEntity.getAddress(),
                storeEntity.getLatitude(),
                storeEntity.getLongitude(),
                storeEntity.getCategory(),
                storeEntity.getCreatedAt()
        );
    }

    public void deleteStore(DeleteStoreRequestDTO deleteStoreRequestDTO) {
        this.storeRepository.findById(deleteStoreRequestDTO.getId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Store not found");
                });

        this.storeRepository.deleteById(deleteStoreRequestDTO.getId());
    }
}
