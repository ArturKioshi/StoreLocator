package com.mapas.maps.dtos.store;

import com.mapas.maps.entities.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindStoresNearbyCoordinatesResponseDTO {
    private List<StoreEntity> stores;
}
