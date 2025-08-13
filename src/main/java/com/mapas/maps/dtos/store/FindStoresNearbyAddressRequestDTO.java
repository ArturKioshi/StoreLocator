package com.mapas.maps.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindStoresNearbyAddressRequestDTO {
    private String address;
    private double radiusKm;
}
