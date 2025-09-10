package gr.aueb.cf.realestateapp.service.static_data_service;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.static_dto.PropertyCategoryResponseDTO;
import gr.aueb.cf.realestateapp.model.static_data.PropertyCategoriesEntity;
import gr.aueb.cf.realestateapp.repository.static_repo.PropertyCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final PropertyCategoryRepository categoryRepository;

    public List<PropertyCategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new PropertyCategoryResponseDTO(
                        category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public Optional<PropertyCategoriesEntity> getCategoryById(Long id) throws AppObjectNotFoundException {
        Optional<PropertyCategoriesEntity> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new AppObjectNotFoundException("", "No category found with id " + id);
        }
        return category;
    }
}
