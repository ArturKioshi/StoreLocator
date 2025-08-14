package com.mapas.maps.dtos.store;

import com.mapas.maps.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStoreResponseDTO {
    private UUID id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private CategoryEnum category;
    private LocalDateTime createdAt;
}
