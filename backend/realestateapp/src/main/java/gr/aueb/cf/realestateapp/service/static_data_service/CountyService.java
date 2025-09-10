package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.CountyResponseDTO;
import gr.aueb.cf.realestateapp.model.static_data.CountyEntity;
import gr.aueb.cf.realestateapp.repository.static_repo.CountyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;

    public List<CountyResponseDTO> getCountiesByRegion(Long regionId) throws AppObjectNotFoundException {
        List<CountyEntity> counties = countyRepository.findByRegionIdOrderByNameAsc(regionId);

        if (counties.isEmpty()) {
            throw new AppObjectNotFoundException(
                    "",
                    "No counties found for region with id " + regionId
            );
        }
        return counties.stream()
                .map(county -> new CountyResponseDTO(county.getId(), county.getName()))
                .collect(Collectors.toList());
    }

    public Optional<CountyEntity> getCountyById(Long id) throws AppObjectNotFoundException {
        Optional<CountyEntity> county = countyRepository.findById(id);

        if (county.isEmpty()) {
            throw new AppObjectNotFoundException("", "No county found with id " + id);
        }
        return county;
    }

//    public List<CountyResponseDTO> getAllCounties() {
//        return countyRepository.findAll()
//                .stream()
//                .map(county -> new CountyResponseDTO(county.getId(), county.getName()))
//                .collect(Collectors.toList());
//    }
}
