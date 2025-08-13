package com.mapas.maps.repositories;

import com.mapas.maps.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
    Optional<StoreEntity> findByAddress(String address);

    @Query(value = """
    SELECT * FROM (
        SELECT s.*,
            (6371 * acos(
                cos(radians(:latitude)) *
                cos(radians(s.latitude)) *
                cos(radians(s.longitude) - radians(:longitude)) +
                sin(radians(:latitude)) *
                sin(radians(s.latitude))
            )) AS distance
        FROM stores s
    ) AS store_with_distance
    WHERE distance <= :radius
    ORDER BY distance
    """, nativeQuery = true)
    List<StoreEntity> findStoresNearby(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius
    );
}
