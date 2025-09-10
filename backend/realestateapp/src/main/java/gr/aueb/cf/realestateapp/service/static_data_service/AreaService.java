package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.AreaResponseDTO;
import gr.aueb.cf.realestateapp.model.static_data.AreaEntity;
import gr.aueb.cf.realestateapp.repository.static_repo.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public List<AreaResponseDTO> getAreasByCounty(Long countyId) throws AppObjectNotFoundException {
        List<AreaEntity> areas = areaRepository.findByCountyIdOrderByNameAsc(countyId);

        if (areas.isEmpty()) {
            throw new AppObjectNotFoundException(
                    "AREA_NOT_FOUND",
                    "No areas found for county with id " + countyId
            );
        }
        return
                areas.stream()
                .map(area -> new AreaResponseDTO(area.getId(), area.getName()))
                .toList();
    }

    public Optional<AreaEntity> getAreaById(Long id) throws AppObjectNotFoundException {
        Optional<AreaEntity> area = areaRepository.findById(id);

        if (area.isEmpty()) {
            throw new AppObjectNotFoundException("", "No area found with id " + id);
        }
        return area;
    }

//    public List<AreaResponseDTO> getAllAreas() {
//        return areaRepository.findAll()
//                .stream()
//                .map( area -> new AreaResponseDTO(area.getId(), area.getName()))
//                .collect(Collectors.toList());
//    }
}
