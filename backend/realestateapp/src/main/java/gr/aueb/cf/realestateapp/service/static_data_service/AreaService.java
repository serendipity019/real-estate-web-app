package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.dto.static_dto.AreaResponseDTO;
import gr.aueb.cf.realestateapp.repository.static_repo.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public List<AreaResponseDTO> getAllAreas() {
        return areaRepository.findAll()
                .stream()
                .map( area -> new AreaResponseDTO(area.getId(), area.getName(), area.getCounty().getId()))
                .collect(Collectors.toList());
    }
}
