package com.edu.estate_agency.service.impl;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.estate_agency.entity.Maintenance;
import com.edu.estate_agency.model.request.CreateMaintenanceRequest;
import com.edu.estate_agency.repository.MaintenanceRepository;
import com.edu.estate_agency.repository.RoomRepository;
import com.edu.estate_agency.service.MaintenanceService;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Maintenance save(CreateMaintenanceRequest createMaintenanceRequest) {
        Maintenance maintenance = new Maintenance();
        maintenance.setName(createMaintenanceRequest.getName());
        maintenance.setPrice(createMaintenanceRequest.getPrice());
        maintenance.setDate(new Date());
        maintenance.setRoom(roomRepository.findById(createMaintenanceRequest.getIdroom()).get());

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public List<Maintenance> getIdRoom(Long id) {
        return maintenanceRepository.getListByRoom(id);
    }

    @Override
    public List<Maintenance> getbtIdAgent(Long id) {
        return maintenanceRepository.getLstAgent(id);

    }

    @Override
    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance upFile(Long id, MultipartFile file) {
     Maintenance maintenance= maintenanceRepository.findById(id).get();
try {
    maintenance.setFile(Base64.getEncoder().encodeToString(file.getBytes()));
} catch (Exception e) {
    
}
return maintenanceRepository.save(maintenance);


    }

    @Override
    public byte[] getFIle(Long id) {
      Maintenance contract= maintenanceRepository.findById(id).get();
      byte[] decodeBytes= Base64.getDecoder().decode(contract.getFile());
      return decodeBytes;
    }

}
