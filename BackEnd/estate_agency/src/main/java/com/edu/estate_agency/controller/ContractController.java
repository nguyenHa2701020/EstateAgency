package com.edu.estate_agency.controller;

import com.edu.estate_agency.entity.Contract;
import com.edu.estate_agency.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class ContractController {
@Autowired
    private ContractService contractService;
@GetMapping("/agent/{id}")
    @Operation(summary = "Get By Agent")
    public ResponseEntity<List<Contract>> getByCustomer(@PathVariable("id") Long id)
{
    List<Contract> od = contractService.getByAgent(id);
    return ResponseEntity.ok(od);
}
    @PostMapping("/upFile")
    @Operation(summary = "Upload File")
    public ResponseEntity<Contract> upload(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(contractService.upFile(id, file));
    }

    @GetMapping("/getfile/{id}")
    @Operation(summary = "Get FIle")
    public ResponseEntity<byte[]> getFIle(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getFile(id));
    }
}
