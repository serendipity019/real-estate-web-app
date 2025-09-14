package gr.aueb.cf.realestateapp.dto.assign_property;

import gr.aueb.cf.realestateapp.core.enums.AssignTypeEnum;
import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AssignPropertyInsertDTO(
        @NotNull(message = "Choose a region")
        Long regionId,

        @NotNull(message = "Choose a county")
        Long countyId,

        @NotNull(message = "Choose a area")
        Long areaId,

        @NotNull(message = "Choose a category")
        Long categoryId,

        @NotNull(message = "Choose a type")
        Long typeId,

        @NotNull(message = "Choose a purpose of use")
        AssignTypeEnum type,

        @NotNull(message = "Choose a status")
        RealEstateStatusEnum status, // This is only for the admin

        @Size(min = 7, message = "Write some words about your property")
        String description,

        @Min(2)
        Integer price,

        @Min(1)
        Integer squareMeters,

        @NotNull(message = "Street is required")
        String street,

        @NotNull(message = "Street Number is required")
        String streetNumber,

        Long postCode
) {
}
