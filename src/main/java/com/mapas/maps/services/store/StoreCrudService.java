package com.mapas.maps.services.store;

import com.mapas.maps.dtos.store.*;
import com.mapas.maps.entities.StoreEntity;
import com.mapas.maps.repositories.StoreRepository;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public GetStoreResponseDTO getStore(GetStoreRequestDTO getStoreRequestDTO) {
        StoreEntity store = this.storeRepository.findById(getStoreRequestDTO.getId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Store not found");
                });

        return new GetStoreResponseDTO(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getLatitude(),
                store.getLongitude(),
                store.getCategory(),
                store.getCreatedAt()
        );
    }

    public GetAllStoresResponseDTO getAllStores() {
        List<StoreEntity> stores = this.storeRepository.findAll();
        return new GetAllStoresResponseDTO(stores);
    }

    public UpdateStoreResponseDTO updateStore(UUID id, UpdateStoreRequestDTO updateStoreRequestDTO) {
        StoreEntity store = this.storeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Store not found");
                });

        if (updateStoreRequestDTO.getName() != null) {
            store.setName(updateStoreRequestDTO.getName());
        }

        if (updateStoreRequestDTO.getCategory() != null) {
            store.setCategory(updateStoreRequestDTO.getCategory());
        }

        StoreEntity updatedStore = this.storeRepository.save(store);

        return new UpdateStoreResponseDTO(
                updatedStore.getId(),
                updatedStore.getName(),
                updatedStore.getAddress(),
                updatedStore.getLatitude(),
                updatedStore.getLongitude(),
                updatedStore.getCategory(),
                updatedStore.getCreatedAt()
        );
    }
}
