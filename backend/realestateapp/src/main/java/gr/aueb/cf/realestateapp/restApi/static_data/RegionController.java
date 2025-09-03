package gr.aueb.cf.realestateapp.restApi.static_data;

import gr.aueb.cf.realestateapp.dto.static_dto.RegionResponseDTO;
import gr.aueb.cf.realestateapp.service.static_data_service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@Tag(name = "Regions", description = "Endpoints for managing regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    @Operation(summary = "Get all regions", description = "Returns a list of all regions")
    public List<RegionResponseDTO> getRegions() {
        return regionService.getAllRegions();
    }
}
