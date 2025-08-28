package gr.aueb.cf.realestateapp.model.repository;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import gr.aueb.cf.realestateapp.model.AssignPropertyEntity;
import gr.aueb.cf.realestateapp.model.RequestPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface RequestPropertyRepository extends JpaRepository<RequestPropertyEntity, Long>,
        JpaSpecificationExecutor<RequestPropertyEntity> {

    Optional<RequestPropertyEntity> findByUuid(String uuid);
    List<RequestPropertyEntity> findByUserId(Long userId);
    List<RequestPropertyEntity> findByRealEstateStatus(PropertyStatusEnum statusEnum);
}
