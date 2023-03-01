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
    private String nameLocation;
    private String adress;
    private float capacityLocation;
    private LocationType locationType;

    private Long idDepartement;
    private String nameDepartment;
    private float capacityDepartment;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
