package com.mapas.maps.dtos.store;

import com.mapas.maps.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStoreRequestDTO {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private CategoryEnum category;
}
