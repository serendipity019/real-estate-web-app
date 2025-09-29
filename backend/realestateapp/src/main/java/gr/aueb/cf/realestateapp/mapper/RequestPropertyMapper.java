package gr.aueb.cf.realestateapp.mapper;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyInsertDTO;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyPublicResponseDTO;
import gr.aueb.cf.realestateapp.model.RequestPropertyEntity;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.model.static_data.*;
import gr.aueb.cf.realestateapp.service.static_data_service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestPropertyMapper {
    private final RegionService regionService;
    private final CountyService countyService;
    private final AreaService areaService;
    private final CategoryService categoryService;
    private final PropertyTypeService propertyTypeService;

    public void mapInsertDTOToEntity(RequestPropertyInsertDTO dto, RequestPropertyEntity entity, UserEntity user) throws AppObjectNotFoundException {
        RegionEntity region = regionService.getRegionById(dto.regionId())
                .orElseThrow(() -> new AppObjectNotFoundException("REGION_NOT_FOUND", "Region with ID: " + dto.regionId() + " not found"));
        CountyEntity county = countyService.getCountyById(dto.countyId())
                .orElseThrow(() -> new AppObjectNotFoundException("COUNTY_NOT_FOUND", "County with ID: " + dto.countyId() + " not found"));
        AreaEntity area = areaService.getAreaById(dto.areaId())
                .orElseThrow(() -> new AppObjectNotFoundException("AREA_NOT_FOUND", "Area with ID: " + dto.areaId() + " not found"));
        PropertyCategoriesEntity category = categoryService.getCategoryById(dto.categoryId())
                .orElseThrow(() -> new AppObjectNotFoundException("CATEGORY_NOT_FOUND", "Category with ID: " + dto.categoryId() + " not found"));
        PropertyTypesEntity type = propertyTypeService.getPropertyTypeById(dto.typeId())
                .orElseThrow(() -> new AppObjectNotFoundException("TYPE_NOT_FOUND", "Type with ID: " + dto.typeId() + " not found"));

        entity.setRegion(region);
        entity.setCounty(county);
        entity.setCategory(category);
        entity.setType(type);
        entity.setRequestPurpose(dto.type());
        entity.setDescription(dto.description());
        entity.setPriceFrom(dto.priceFrom());
        entity.setPriceTo(dto.priceTo());
        entity.setSquareMetersFrom(dto.squareMetersFrom());
        entity.setSquareMetersTo(dto.squareMetersTo());
        entity.setUser(user);
    }

    public RequestPropertyAdminResponseDTO mapEntityToAdminResponseDTO(RequestPropertyEntity entity) {
        return new RequestPropertyAdminResponseDTO(
                entity.getUuid(),
                entity.getRegion().getName(),
                entity.getCounty().getName(),
                entity.getArea().getName(),
                entity.getCategory().getName(),
                entity.getType().getName(),
                entity.getDescription(),
                entity.getPriceFrom(),
                entity.getPriceTo(),
                entity.getSquareMetersFrom(),
                entity.getSquareMetersTo(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getRealEstateStatus(),
                entity.getCreatedBy(),
                entity.getLastModifiedBy()
        );
    }

    public RequestPropertyPublicResponseDTO mapEntityToPublicResponseDTO(RequestPropertyEntity entity) {
        return new RequestPropertyPublicResponseDTO(
                entity.getUuid(),
                entity.getRegion().getName(),
                entity.getCounty().getName(),
                entity.getArea().getName(),
                entity.getCategory().getName(),
                entity.getType().getName(),
                entity.getDescription(),
                entity.getPriceFrom(),
                entity.getPriceTo(),
                entity.getSquareMetersFrom(),
                entity.getSquareMetersTo(),
                entity.getUpdatedAt()
        );
    }
}
