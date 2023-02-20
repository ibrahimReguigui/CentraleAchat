package com.CentraleAchat.Inventoryservice.dto;

import com.CentraleAchat.Inventoryservice.entities.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LocationDto {
    private Long idLocation;
    private Long idSupplier;
    private String name;
    private String adress;
    private float capacity;
    private LocationType locationType;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
