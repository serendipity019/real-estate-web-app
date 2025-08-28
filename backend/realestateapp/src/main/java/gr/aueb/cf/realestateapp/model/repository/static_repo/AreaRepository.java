package gr.aueb.cf.realestateapp.model.repository.static_repo;

import gr.aueb.cf.realestateapp.model.static_data.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<AreaEntity, Long>,
        JpaSpecificationExecutor<AreaEntity> {

    Optional<AreaEntity> findByName(String name);

    List<AreaEntity> findByCountyIdOrderByNameAsc(Long countyId);
}
