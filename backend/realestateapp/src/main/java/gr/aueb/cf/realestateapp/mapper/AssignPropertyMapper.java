package gr.aueb.cf.realestateapp.mapper;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyInsertDTO;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyPublicResponseDTO;
import gr.aueb.cf.realestateapp.model.AssignPropertyEntity;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.model.static_data.*;
import gr.aueb.cf.realestateapp.service.static_data_service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignPropertyMapper {
    private final RegionService regionService;
    private final CountyService countyService;
    private final AreaService areaService;
    private final CategoryService categoryService;
    private final PropertyTypeService propertyTypeService;

    public void mapInsertDTOToEntity(AssignPropertyInsertDTO dto, AssignPropertyEntity assignProperty, UserEntity user)
            throws AppObjectNotFoundException {
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


        assignProperty.setRegion(region);
        assignProperty.setCounty(county);
        assignProperty.setArea(area);
        assignProperty.setCategory(category);
        assignProperty.setType(type);
        assignProperty.setAssignPurpose(dto.type());
        assignProperty.setDescription(dto.description());
        assignProperty.setPrice(dto.price());
        assignProperty.setSquareMeters(dto.squareMeters());
        assignProperty.setStreet(dto.street());
        assignProperty.setStreetNumber(dto.streetNumber());
        assignProperty.setPostCode(dto.postCode());
    }

    public AssignPropertyAdminResponseDTO mapAssignToAdminResponseDTO(AssignPropertyEntity assign) {
       return new AssignPropertyAdminResponseDTO(
               assign.getUuid(),
               assign.getRegion().getName(),
               assign.getCounty().getName(),
               assign.getArea().getName(),
               assign.getCategory().getName(),
               assign.getType().getName(),
               assign.getRealEstateStatus(),
               assign.getAssignPurpose(),
               assign.getDescription(),
               assign.getPrice(),
               assign.getSquareMeters(),
               assign.getStreet(),
               assign.getStreetNumber(),
               assign.getPostCode(),
               assign.getCreatedBy(),
               assign.getLastModifiedBy(),
               assign.getCreatedAt(),
               assign.getUpdatedAt()
       );
    }

    public AssignPropertyPublicResponseDTO mapAssignToPublicResponseDTO(AssignPropertyEntity assign) {
        return new AssignPropertyPublicResponseDTO(
                assign.getUuid(),
                assign.getRegion().getName(),
                assign.getCounty().getName(),
                assign.getArea().getName(),
                assign.getCategory().getName(),
                assign.getType().getName(),
                assign.getAssignPurpose(),
                assign.getDescription(),
                assign.getPrice(),
                assign.getSquareMeters(),
                assign.getStreet(),
                assign.getStreetNumber(),
                assign.getPostCode(),
                assign.getCreatedAt(),
                assign.getUpdatedAt()
        );
    }
}
