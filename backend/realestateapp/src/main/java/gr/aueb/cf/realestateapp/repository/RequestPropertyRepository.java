package gr.aueb.cf.realestateapp.repository;

import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import gr.aueb.cf.realestateapp.core.enums.RequestTypeEnum;
import gr.aueb.cf.realestateapp.model.RequestPropertyEntity;
import gr.aueb.cf.realestateapp.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface RequestPropertyRepository extends JpaRepository<RequestPropertyEntity, Long>,
        JpaSpecificationExecutor<RequestPropertyEntity> {

    Optional<RequestPropertyEntity> findByUuid(String uuid);
    List<RequestPropertyEntity> findByUser(UserEntity user);
    List<RequestPropertyEntity> findByRealEstateStatus(RealEstateStatusEnum statusEnum);
    Page<RequestPropertyEntity> findByRealEstateStatus(RealEstateStatusEnum statusEnum, Pageable pageable);
    List<RequestPropertyEntity> findAll();
    Page<RequestPropertyEntity> findAll(Pageable pageable);
    List<RequestPropertyEntity> findByRequestPurpose(RequestTypeEnum typeEnum);
    Page<RequestPropertyEntity> findByRequestPurpose(RequestTypeEnum typeEnum, Pageable pageable);
}
