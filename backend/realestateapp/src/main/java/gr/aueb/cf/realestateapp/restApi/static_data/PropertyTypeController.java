package gr.aueb.cf.realestateapp.restApi.static_data;

import gr.aueb.cf.realestateapp.dto.static_dto.PropertyTypeResponseDTO;
import gr.aueb.cf.realestateapp.service.static_data_service.PropertyTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@AllArgsConstructor
@Tag(name = "CategoryTypes", description = "Endpoints for managing property types")
public class PropertyTypeController {
    private PropertyTypeService typeService;

    public List<PropertyTypeResponseDTO> getTypes(@RequestParam Long categoryId) {
        return typeService.getPropertyTypesByCategory(categoryId);
    }
}
