package gr.aueb.cf.realestateapp.restApi.static_data;

import gr.aueb.cf.realestateapp.dto.static_dto.PropertyCategoryResponseDTO;
import gr.aueb.cf.realestateapp.service.static_data_service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Tag(name = "Categories", description = "Endpoints for managing property categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Returns a list of all categories")
    public List<PropertyCategoryResponseDTO> getCategories() {
        return categoryService.getAllCategories();
    }
}
