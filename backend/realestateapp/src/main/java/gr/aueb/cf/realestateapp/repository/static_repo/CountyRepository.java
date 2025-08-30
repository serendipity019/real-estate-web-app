package gr.aueb.cf.realestateapp.repository.static_repo;

import gr.aueb.cf.realestateapp.model.static_data.CountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CountyRepository extends JpaRepository<CountyEntity, Long>,
        JpaSpecificationExecutor<CountyEntity> {

    Optional<CountyEntity> findByName(String name);

    List<CountyEntity> findByRegionIdOrderByNameAsc(Long regionId);
}
