package gr.aueb.cf.realestateapp.restApi.static_data;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.PropertyTypeResponseDTO;
import gr.aueb.cf.realestateapp.service.static_data_service.PropertyTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@AllArgsConstructor
@Tag(name = "CategoryTypes", description = "Endpoints for managing property types")
public class PropertyTypeController {
    private PropertyTypeService typeService;

    @GetMapping("/{categoryId}")
    @Operation(summary = "Get all types", description = "Returns a list of property types with specific categoryId")
    public List<PropertyTypeResponseDTO> getTypes(@PathVariable Long categoryId) throws AppObjectNotFoundException {
        return typeService.getPropertyTypesByCategory(categoryId);
    }
}
