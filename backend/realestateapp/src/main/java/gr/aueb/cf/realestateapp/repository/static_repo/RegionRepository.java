package gr.aueb.cf.realestateapp.repository.static_repo;

import gr.aueb.cf.realestateapp.model.static_data.RegionEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, Long>,
        JpaSpecificationExecutor<RegionEntity> {

    Optional<RegionEntity> findByName(String name);
}
