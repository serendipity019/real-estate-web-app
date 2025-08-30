package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.dto.static_dto.CountyResponseDTO;
import gr.aueb.cf.realestateapp.repository.static_repo.CountyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;

    public List<CountyResponseDTO> getAllCounties() {
        return countyRepository.findAll()
                .stream()
                .map(county -> new CountyResponseDTO(county.getId(), county.getName(), county.getRegion().getId()))
                .collect(Collectors.toList());
    }
}
