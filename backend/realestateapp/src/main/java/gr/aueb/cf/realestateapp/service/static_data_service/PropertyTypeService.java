package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.dto.static_dto.PropertyTypeResponseDTO;
import gr.aueb.cf.realestateapp.repository.static_repo.PropertyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyTypeService {
    private final PropertyTypeRepository typeRepository;

    public List<PropertyTypeResponseDTO> getAllPropertyTypes() {
        return typeRepository.findAll()
                .stream()
                .map(type -> new PropertyTypeResponseDTO(
                        type.getId(), type.getName(), type.getCategory().getId()))
                .collect(Collectors.toList());
    }
}
