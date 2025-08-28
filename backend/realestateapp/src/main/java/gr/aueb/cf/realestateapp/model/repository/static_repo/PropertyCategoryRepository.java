package gr.aueb.cf.realestateapp.model.repository.static_repo;

import gr.aueb.cf.realestateapp.model.static_data.PropertyCategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PropertyCategoryRepository extends JpaRepository<PropertyCategoriesEntity, Long>,
        JpaSpecificationExecutor<PropertyCategoriesEntity> {

    Optional<PropertyCategoriesEntity> findByName(String name);
}
