package gr.aueb.cf.realestateapp.repository.static_repo;

import gr.aueb.cf.realestateapp.model.static_data.PropertyCategoriesEntity;
import gr.aueb.cf.realestateapp.model.static_data.PropertyTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PropertyTypeRepository extends JpaRepository<PropertyTypesEntity, Long>,
        JpaSpecificationExecutor<PropertyTypesEntity> {

    Optional<PropertyTypesEntity> findByName(String name);

    List<PropertyTypesEntity> findByCategoryId(Long categoryId);
}
