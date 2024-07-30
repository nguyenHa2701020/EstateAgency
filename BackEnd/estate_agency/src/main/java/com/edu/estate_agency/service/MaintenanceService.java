package com.edu.estate_agency.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.edu.estate_agency.entity.Maintenance;
import com.edu.estate_agency.model.request.CreateMaintenanceRequest;

public interface MaintenanceService {
    Maintenance save(CreateMaintenanceRequest createMaintenanceRequest);

    List<Maintenance> getIdRoom(Long id);

    List<Maintenance> getbtIdAgent(Long id);

    List<Maintenance> getAll();

    Maintenance upFile(Long id, MultipartFile file);

    byte[] getFIle(Long id);

}
