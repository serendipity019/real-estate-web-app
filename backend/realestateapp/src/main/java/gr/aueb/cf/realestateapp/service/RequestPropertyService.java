package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import gr.aueb.cf.realestateapp.core.enums.RequestTypeEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyAdminResponseDTO;
import gr.aueb.cf.realestateapp.dto.request_property.RequestPropertyInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestPropertyService {
    RequestPropertyAdminResponseDTO createRequestProperty(RequestPropertyInsertDTO insertDTO, String email)
            throws AppObjectNotFoundException;
    RequestPropertyAdminResponseDTO updateRequestProperty(String uuid, RequestPropertyInsertDTO updateDTO, String email)
            throws AppObjectNotAuthorizedException, AppObjectNotFoundException;
    void deleteRequestProperty(String uuid, String email)
            throws AppObjectNotAuthorizedException;
    List<RequestPropertyAdminResponseDTO> getRequestPropertyByUser(String email);
    List<RequestPropertyAdminResponseDTO> getAllRequestProperties();
    Page<RequestPropertyAdminResponseDTO> getAllRequestProperties(Pageable pageable);
    List<RequestPropertyAdminResponseDTO> getRequestPropertiesByRealEstateStatus(PropertyStatusEnum statusEnum);
    Page<RequestPropertyAdminResponseDTO> getRequestPropertiesByRealEstateStatus(PropertyStatusEnum statusEnum, Pageable pageable);
    List<RequestPropertyAdminResponseDTO> getRequestPropertyByRequestType(RequestTypeEnum typeEnum);
    Page<RequestPropertyAdminResponseDTO> getRequestPropertyByRequestType(RequestTypeEnum typeEnum, Pageable pageable);
}
