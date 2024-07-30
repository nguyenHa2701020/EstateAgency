package com.edu.estate_agency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.estate_agency.entity.Maintenance;
import com.edu.estate_agency.model.request.CreateMaintenanceRequest;
import com.edu.estate_agency.service.MaintenanceService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class MainenanceController {
    @Autowired
    private MaintenanceService maintenanceService;
    @GetMapping("/all/{id}")
    @Operation(summary = "All Maintaince")
    public ResponseEntity<?> getMRequest(@PathVariable("id") long id)
    {
        List<Maintenance> requests= maintenanceService.getbtIdAgent(id);
        return ResponseEntity.ok(requests);
        
    }
    @PostMapping("/save")
    @Operation(summary = "Save Maintaince")
    public ResponseEntity<?> postMethodName(@RequestBody CreateMaintenanceRequest createMaintenanceRequest)
    {
        log.info("Hello"+ createMaintenanceRequest.toString());
        return ResponseEntity.ok(maintenanceService.save(createMaintenanceRequest));

    }
    @PostMapping("/upFile")
    @Operation(summary = "up file Maintaince")
    public ResponseEntity<Maintenance> upload(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file)
    {
        return ResponseEntity.ok(maintenanceService.upFile(id, file));

    }
    @GetMapping("/getfile/{id}")
    @Operation(summary = "get file Maintenance")
public ResponseEntity<byte[]> getFile(@PathVariable Long id)
{
    return ResponseEntity.ok(maintenanceService.getFIle(id));
}

}
