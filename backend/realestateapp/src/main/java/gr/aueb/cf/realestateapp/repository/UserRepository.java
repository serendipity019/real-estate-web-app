package gr.aueb.cf.realestateapp.repository;

import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import gr.aueb.cf.realestateapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findByRole(RoleEnum role);
    boolean existsByEmail(String email);
    boolean isAdminOrAgent(RoleEnum role);
    boolean isAdmin(RoleEnum role);
}
