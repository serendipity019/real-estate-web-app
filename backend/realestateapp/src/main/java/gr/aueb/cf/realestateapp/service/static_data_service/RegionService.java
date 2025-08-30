package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.dto.static_dto.RegionResponseDTO;
import gr.aueb.cf.realestateapp.repository.static_repo.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<RegionResponseDTO> getAllRegions() {
        return regionRepository.findAll()
                .stream()
                .map(region -> new RegionResponseDTO(region.getId(), region.getName()))
                .collect(Collectors.toList());
    }

}
