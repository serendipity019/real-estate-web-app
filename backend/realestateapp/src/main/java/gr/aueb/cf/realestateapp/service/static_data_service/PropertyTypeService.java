package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.PropertyTypeResponseDTO;
import gr.aueb.cf.realestateapp.model.static_data.PropertyTypesEntity;
import gr.aueb.cf.realestateapp.repository.static_repo.PropertyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PropertyTypeService {
    private final PropertyTypeRepository typeRepository;

    public List<PropertyTypeResponseDTO> getPropertyTypesByCategory(Long categoryId) throws AppObjectNotFoundException {
        List<PropertyTypesEntity> types = typeRepository.findByCategoryId(categoryId);

        if (types.isEmpty()) {
            throw new AppObjectNotFoundException(
                    "TYPE_NOT_FOUND",
                    "No areas found for property category with id " + categoryId
            );
        }

        return types
                .stream()
                .map(type -> new PropertyTypeResponseDTO(
                        type.getId(), type.getName()))
                .toList();
    }

    public Optional<PropertyTypesEntity> getPropertyTypeById(Long id) throws AppObjectNotFoundException {
        Optional<PropertyTypesEntity> type = typeRepository.findById(id);

        if (type.isEmpty()) {
            throw new AppObjectNotFoundException("", "No property type found with id " + id);
        }
        return type;
    }

//    public List<PropertyTypeResponseDTO> getAllPropertyTypes() {
//        return typeRepository.findAll()
//                .stream()
//                .map(type -> new PropertyTypeResponseDTO(
//                        type.getId(), type.getName()))
//                .collect(Collectors.toList());
//    }
}
