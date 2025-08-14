package com.mapas.maps.dtos.store;

import com.mapas.maps.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStoreRequestDTO {
    private String name;
    private CategoryEnum category;
}
