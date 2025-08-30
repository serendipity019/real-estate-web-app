package gr.aueb.cf.realestateapp.dto.request_property;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import gr.aueb.cf.realestateapp.core.enums.RequestTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestPropertyInsertDTO(
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
        RequestTypeEnum type,

        @NotNull(message = "Choose a status")
        PropertyStatusEnum status, // This is only for the admin

        @Size(min = 7, message = "Write some words about your property")
        String description,

        @Min(2)
        Integer priceFrom,

        @Min(2)
        Integer priceTo,

        @Min(1)
        Integer squareMetersFrom,

        @Min(1)
        Integer squareMetersTo
) {
        public RequestPropertyInsertDTO {
                if (priceTo != null && priceFrom != null && priceTo < priceFrom) {
                        throw new IllegalArgumentException("priceTo must be greater than or equal to priceFrom");
                }

                if (squareMetersFrom != null && squareMetersTo != null && squareMetersTo < squareMetersFrom) {
                        throw new IllegalArgumentException("priceTo must be greater than or equal to priceFrom");
                }
        }
}
