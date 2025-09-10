package gr.aueb.cf.realestateapp.restApi.static_data;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.AreaResponseDTO;
import gr.aueb.cf.realestateapp.service.static_data_service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
@Tag(name = "areas", description = "Endpoints for managing areas")
public class AreaController {
    private final AreaService areaService;

    @GetMapping("/{countyId}")
    @Operation(summary = "Get all areas", description = "Returns a list of areas with specific countyId")
    public List<AreaResponseDTO> getAreas(
            @PathVariable Long countyId
    ) throws AppObjectNotFoundException {
        return areaService.getAreasByCounty(countyId);
    }
}
