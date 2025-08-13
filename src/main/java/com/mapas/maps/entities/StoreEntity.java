package com.mapas.maps.entities;

import com.mapas.maps.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "stores")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
