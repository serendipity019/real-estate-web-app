package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.AssignTypeEnum;
import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyInsertDTO;
import gr.aueb.cf.realestateapp.mapper.AssignPropertyMapper;
import gr.aueb.cf.realestateapp.model.AssignPropertyEntity;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.repository.AssignPropertyRepository;
import gr.aueb.cf.realestateapp.repository.UserRepository;
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
public class AssignPropertyServiceImpl implements AssignPropertyService {
    private final AssignPropertyRepository assignRepository;
    private final AssignPropertyMapper assignMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public AssignPropertyAdminResponseDTO createAssignProperty(AssignPropertyInsertDTO insertDTO, String email) throws AppObjectNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        AssignPropertyEntity assignProperty = new AssignPropertyEntity();
        assignMapper.mapInsertDTOToEntity(insertDTO, assignProperty, user);

        AssignPropertyEntity savedAssign = assignRepository.save(assignProperty);
        return assignMapper.mapAssignToAdminResponseDTO(savedAssign);
    }

    @Override
    public AssignPropertyAdminResponseDTO updateAssignProperty(String uuid, AssignPropertyInsertDTO updateDTO, String email) throws AppObjectNotAuthorizedException, AppObjectNotFoundException {
        AssignPropertyEntity assignProperty = assignRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Request property with UUID: " + uuid + " not found."));

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        if (!assignProperty.getUser().getEmail().equals(email) && (!userService.userIsAdminOrAgent(user.getId()))) {
            throw new AppObjectNotAuthorizedException("NOT_AUTHORIZED", "You are not authorized to update this property");
        }

        assignMapper.mapInsertDTOToEntity(updateDTO, assignProperty, assignProperty.getUser());

        // Only admin or agent can update status. Is the real estate status.
        if (userService.userIsAdminOrAgent(user.getId())) {
            assignProperty.setRealEstateStatus(updateDTO.status());
        }

        AssignPropertyEntity updatedAssign = assignRepository.save(assignProperty);
        return assignMapper.mapAssignToAdminResponseDTO(updatedAssign);
    }

    @Override
    public void deleteAssignProperty(String uuid, String email) throws AppObjectNotAuthorizedException {
        AssignPropertyEntity assignProperty = assignRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Request property with UUID: " + uuid + " not found."));

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));

        if (!assignProperty.getUser().getEmail().equals(email) && (!userService.userIsAdminOrAgent(user.getId()))) {
            throw new AppObjectNotAuthorizedException("NOT_AUTHORIZED", "You are not authorized to delete this property");
        }

        assignRepository.delete(assignProperty);
    }

    @Override
    public List<AssignPropertyAdminResponseDTO> getAssignPropertyByUser(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found "));
        return assignRepository.findByUser(user)
                .stream()
                .map(assignMapper::mapAssignToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignPropertyAdminResponseDTO> getAllAssignProperties() {
        return assignRepository.findAll()
                .stream()
                .map(assignMapper::mapAssignToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AssignPropertyAdminResponseDTO> getAllAssignProperties(Pageable pageable) {
        return  assignRepository.findAll(pageable)
                .map(assignMapper::mapAssignToAdminResponseDTO);
    }

    @Override
    public List<AssignPropertyAdminResponseDTO> getAssignPropertiesByRealEstateStatus(RealEstateStatusEnum statusEnum) {
        return assignRepository.findByRealEstateStatus(statusEnum)
                .stream()
                .map(assignMapper::mapAssignToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AssignPropertyAdminResponseDTO> getAssignPropertiesByRealEstateStatus(RealEstateStatusEnum statusEnum, Pageable pageable) {
        return assignRepository.findByRealEstateStatus(statusEnum, pageable)
                .map(assignMapper::mapAssignToAdminResponseDTO);
    }

    @Override
    public List<AssignPropertyAdminResponseDTO> getAssignPropertyByRequestType(AssignTypeEnum typeEnum) {
        return assignRepository.findByRequestPurpose(typeEnum)
                .stream()
                .map(assignMapper::mapAssignToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AssignPropertyAdminResponseDTO> getAssignPropertyByRequestType(AssignTypeEnum typeEnum, Pageable pageable) {
        return assignRepository.findByRequestPurpose(typeEnum, pageable)
                .map(assignMapper::mapAssignToAdminResponseDTO);
    }
}
