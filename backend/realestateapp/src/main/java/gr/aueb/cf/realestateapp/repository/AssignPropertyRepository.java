package gr.aueb.cf.realestateapp.repository;

import gr.aueb.cf.realestateapp.core.enums.AssignTypeEnum;
import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import gr.aueb.cf.realestateapp.model.AssignPropertyEntity;
import gr.aueb.cf.realestateapp.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AssignPropertyRepository extends JpaRepository<AssignPropertyEntity, Long>,
        JpaSpecificationExecutor<AssignPropertyEntity> {

        Optional<AssignPropertyEntity> findByUuid(String uuid);
        List<AssignPropertyEntity> findByUser(UserEntity user);
        List<AssignPropertyEntity> findAll();
        Page<AssignPropertyEntity> findAll(Pageable pageable);
        List<AssignPropertyEntity> findByRealEstateStatus(RealEstateStatusEnum statusEnum);
        Page<AssignPropertyEntity> findByRealEstateStatus(RealEstateStatusEnum statusEnum, Pageable pageable);
        List<AssignPropertyEntity> findByRequestPurpose(AssignTypeEnum typeEnum);
        Page<AssignPropertyEntity> findByRequestPurpose(AssignTypeEnum typeEnum, Pageable pageable);
}
