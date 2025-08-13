package com.mapas.maps.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindStoresNearbyCoordinatesRequestDTO {
    private double lat;
    private double lon;
    private double radiusKm;
}
