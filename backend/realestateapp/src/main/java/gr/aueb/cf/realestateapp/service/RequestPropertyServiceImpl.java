package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import gr.aueb.cf.realestateapp.core.enums.RequestTypeEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyInsertDTO;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyPublicResponseDTO;
import gr.aueb.cf.realestateapp.model.RequestPropertyEntity;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.model.static_data.*;
import gr.aueb.cf.realestateapp.repository.RequestPropertyRepository;
import gr.aueb.cf.realestateapp.repository.UserRepository;
import gr.aueb.cf.realestateapp.service.static_data_service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RequestPropertyServiceImpl implements RequestPropertyService{
    private final RequestPropertyRepository requestPropertyRepository;
    private final UserRepository userRepository;
    private final RegionService regionService;
    private final CountyService countyService;
    private final AreaService areaService;
    private final CategoryService categoryService;
    private final PropertyTypeService propertyTypeService;
    private final UserService userService;

    // Create Request Property
    @Override
    public RequestPropertyAdminResponseDTO createRequestProperty(RequestPropertyInsertDTO insertDTO, String email) throws AppObjectNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        RequestPropertyEntity requestProperty = new RequestPropertyEntity();
        mapInsertDTOToEntity(insertDTO, requestProperty, user);

        RequestPropertyEntity savedRequest = requestPropertyRepository.save(requestProperty);
        return mapEntityToAdminResponseDTO(savedRequest);
    }

    // Update Request Property
    @Override
    public RequestPropertyAdminResponseDTO updateRequestProperty(String uuid, RequestPropertyInsertDTO updateDTO, String email) throws AppObjectNotAuthorizedException, AppObjectNotFoundException {
        RequestPropertyEntity requestProperty = requestPropertyRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Request property with UUID: " + uuid + " not found."));

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        if (!requestProperty.getUser().getEmail().equals(email) && (!userService.userIsAdminOrAgent(user.getId()))) {
            throw new AppObjectNotAuthorizedException("NOT_AUTHORIZED", "You are not authorized to update this property");
        }

        mapInsertDTOToEntity(updateDTO, requestProperty, requestProperty.getUser());

        // Only admin or agent can update status. Is the real estate status.
        if (userService.userIsAdminOrAgent(user.getId())) {
            requestProperty.setRealEstateStatus(updateDTO.status());
        }

        RequestPropertyEntity updatedRequest = requestPropertyRepository.save(requestProperty);
        return mapEntityToAdminResponseDTO(updatedRequest);
    }

    // Delete Request Property
    @Override
    public void deleteRequestProperty(String uuid, String email) throws AppObjectNotAuthorizedException {
        RequestPropertyEntity requestProperty = requestPropertyRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Request property with UUID: " + uuid + " not found."));

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        if (!requestProperty.getUser().getEmail().equals(email) && (!userService.userIsAdminOrAgent(user.getId()))) {
            throw new AppObjectNotAuthorizedException("NOT_AUTHORIZED", "You are not authorized to delete this property");
        }

        requestPropertyRepository.delete(requestProperty);
    }

    //This method find Request Property by the user that created.
    @Override
    public List<RequestPropertyAdminResponseDTO> getRequestPropertyByUser(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        return requestPropertyRepository.findByUser(user)
                .stream()
                .map(this::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    // Find all
    @Override
    public List<RequestPropertyAdminResponseDTO> getAllRequestProperties() {
        return requestPropertyRepository.findAll()
                .stream()
                .map(this::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    // Find all pageable
    @Override
    public Page<RequestPropertyAdminResponseDTO> getAllRequestProperties(Pageable pageable) {
        return requestPropertyRepository.findAll(pageable)
                .map(this::mapEntityToAdminResponseDTO);
    }

    // Find by real estate status
    @Override
    public List<RequestPropertyAdminResponseDTO> getRequestPropertiesByRealEstateStatus(PropertyStatusEnum statusEnum) {
        return requestPropertyRepository.findByRealEstateStatus(statusEnum)
                .stream()
                .map(this::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    // Find by real estate status pageable
    @Override
    public Page<RequestPropertyAdminResponseDTO> getRequestPropertiesByRealEstateStatus(PropertyStatusEnum statusEnum, Pageable pageable) {
        return requestPropertyRepository.findByRealEstateStatus(statusEnum, pageable)
                .map(this::mapEntityToAdminResponseDTO);
    }

    // Find by request type e.g RENT Or BUY
    @Override
    public List<RequestPropertyAdminResponseDTO> getRequestPropertyByRequestType(RequestTypeEnum typeEnum) {
        return requestPropertyRepository.findByRequestPurpose(typeEnum)
                .stream()
                .map(this::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }
    // Find by request type pageable
    @Override
    public Page<RequestPropertyAdminResponseDTO> getRequestPropertyByRequestType(RequestTypeEnum typeEnum, Pageable pageable) {
        return requestPropertyRepository.findByRequestPurpose(typeEnum, pageable)
                .map(this::mapEntityToAdminResponseDTO);
    }

    private void mapInsertDTOToEntity(RequestPropertyInsertDTO dto, RequestPropertyEntity entity, UserEntity user) throws AppObjectNotFoundException {
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

    private RequestPropertyAdminResponseDTO mapEntityToAdminResponseDTO(RequestPropertyEntity entity) {
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

    private RequestPropertyPublicResponseDTO mapEntityToPublicResponseDTO(RequestPropertyEntity entity) {
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