package com.Kosten.Api_Rest.service;

import com.Kosten.Api_Rest.dto.BaseResponse;
import com.Kosten.Api_Rest.dto.ExtendedBaseResponse;
import com.Kosten.Api_Rest.dto.packageDTO.PackageRequestDTO;
import com.Kosten.Api_Rest.dto.packageDTO.PackageResponseDTO;
import com.Kosten.Api_Rest.dto.packageDTO.PackageToUpdateDTO;
import com.Kosten.Api_Rest.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PackageService {

    ExtendedBaseResponse<PackageResponseDTO> createPackage(PackageRequestDTO packageRequestDTO, List<MultipartFile> images, MultipartFile bannerImage);
    ExtendedBaseResponse<PackageResponseDTO> getPackageById(Long id);
    ExtendedBaseResponse<Page<PackageResponseDTO>> getAllActivePackages(Pageable pageable);
    ExtendedBaseResponse<Page<PackageResponseDTO>> getAllPackages(Pageable pageable);
    ExtendedBaseResponse<PackageResponseDTO> update(PackageToUpdateDTO packageToUpdateDTO);
    BaseResponse delete(Long id);

}
