package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.AssignTypeEnum;
import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.assign_property.AssignPropertyInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssignPropertyService {
    AssignPropertyAdminResponseDTO createAssignProperty(AssignPropertyInsertDTO insertDTO, String email)
            throws AppObjectNotFoundException;
    AssignPropertyAdminResponseDTO updateAssignProperty(String uuid, AssignPropertyInsertDTO updateDTO, String email)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;
    void deleteAssignProperty(String uuid, String email)
            throws AppObjectNotAuthorizedException;
    List<AssignPropertyAdminResponseDTO> getAssignPropertyByUser(String email);
    List<AssignPropertyAdminResponseDTO> getAllAssignProperties();
    Page<AssignPropertyAdminResponseDTO> getAllAssignProperties(Pageable pageable);
    List<AssignPropertyAdminResponseDTO> getAssignPropertiesByRealEstateStatus(RealEstateStatusEnum statusEnum);
    Page<AssignPropertyAdminResponseDTO> getAssignPropertiesByRealEstateStatus(RealEstateStatusEnum statusEnum, Pageable pageable);
    List<AssignPropertyAdminResponseDTO> getAssignPropertyByRequestType(AssignTypeEnum typeEnum);
    Page<AssignPropertyAdminResponseDTO> getAssignPropertyByRequestType(AssignTypeEnum typeEnum, Pageable pageable);
}
