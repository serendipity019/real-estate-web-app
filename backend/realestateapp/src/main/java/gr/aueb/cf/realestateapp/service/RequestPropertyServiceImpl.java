package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import gr.aueb.cf.realestateapp.core.enums.RequestTypeEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyInsertDTO;
import gr.aueb.cf.realestateapp.mapper.RequestPropertyMapper;
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
    private final UserService userService;
    private final RequestPropertyMapper propertyMapper;

    // Create Request Property
    @Override
    public RequestPropertyAdminResponseDTO createRequestProperty(RequestPropertyInsertDTO insertDTO, String email) throws AppObjectNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        RequestPropertyEntity requestProperty = new RequestPropertyEntity();
        propertyMapper.mapInsertDTOToEntity(insertDTO, requestProperty, user);

        RequestPropertyEntity savedRequest = requestPropertyRepository.save(requestProperty);
        return propertyMapper.mapEntityToAdminResponseDTO(savedRequest);
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

        propertyMapper.mapInsertDTOToEntity(updateDTO, requestProperty, requestProperty.getUser());

        // Only admin or agent can update status. Is the real estate status.
        if (userService.userIsAdminOrAgent(user.getId())) {
            requestProperty.setRealEstateStatus(updateDTO.status());
        }

        RequestPropertyEntity updatedRequest = requestPropertyRepository.save(requestProperty);
        return propertyMapper.mapEntityToAdminResponseDTO(updatedRequest);
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
                .map(propertyMapper::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    // Find all
    @Override
    public List<RequestPropertyAdminResponseDTO> getAllRequestProperties() {
        return requestPropertyRepository.findAll()
                .stream()
                .map(propertyMapper::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    // Find all pageable
    @Override
    public Page<RequestPropertyAdminResponseDTO> getAllRequestProperties(Pageable pageable) {
        return requestPropertyRepository.findAll(pageable)
                .map(propertyMapper::mapEntityToAdminResponseDTO);
    }

    // Find by real estate status
    @Override
    public List<RequestPropertyAdminResponseDTO> getRequestPropertiesByRealEstateStatus(RealEstateStatusEnum statusEnum) {
        return requestPropertyRepository.findByRealEstateStatus(statusEnum)
                .stream()
                .map(propertyMapper::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    // Find by real estate status pageable
    @Override
    public Page<RequestPropertyAdminResponseDTO> getRequestPropertiesByRealEstateStatus(RealEstateStatusEnum statusEnum, Pageable pageable) {
        return requestPropertyRepository.findByRealEstateStatus(statusEnum, pageable)
                .map(propertyMapper::mapEntityToAdminResponseDTO);
    }

    // Find by request type e.g RENT Or BUY
    @Override
    public List<RequestPropertyAdminResponseDTO> getRequestPropertyByRequestType(RequestTypeEnum typeEnum) {
        return requestPropertyRepository.findByRequestPurpose(typeEnum)
                .stream()
                .map(propertyMapper::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }
    // Find by request type pageable
    @Override
    public Page<RequestPropertyAdminResponseDTO> getRequestPropertyByRequestType(RequestTypeEnum typeEnum, Pageable pageable) {
        return requestPropertyRepository.findByRequestPurpose(typeEnum, pageable)
                .map(propertyMapper::mapEntityToAdminResponseDTO);
    }
}