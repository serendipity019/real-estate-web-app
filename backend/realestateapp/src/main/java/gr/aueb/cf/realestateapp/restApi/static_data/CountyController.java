package gr.aueb.cf.realestateapp.restApi.static_data;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.CountyResponseDTO;
import gr.aueb.cf.realestateapp.service.static_data_service.CountyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/counties")
@RequiredArgsConstructor
@Tag(name = "Counties", description = "Endpoints for managing counties")
public class CountyController {
    private final CountyService countyService;

    @GetMapping("/{regionId}")
    @Operation(summary = "Get counties", description = "Returns a list of counties with specific regionId")
    public List<CountyResponseDTO> getCounties(
            @RequestParam Long regionId
    ) throws AppObjectNotFoundException {
            return countyService.getCountiesByRegion(regionId);
    }
}
