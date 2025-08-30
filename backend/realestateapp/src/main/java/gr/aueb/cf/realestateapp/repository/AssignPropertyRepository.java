package gr.aueb.cf.realestateapp.repository;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import gr.aueb.cf.realestateapp.model.AssignPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AssignPropertyRepository extends JpaRepository<AssignPropertyEntity, Long>,
        JpaSpecificationExecutor<AssignPropertyEntity> {

        Optional<AssignPropertyEntity> findByUuid(String uuid);
        List<AssignPropertyEntity> findByUserId(Long userId);
        List<AssignPropertyEntity> findByRealEstateStatus(PropertyStatusEnum statusEnum);
}
