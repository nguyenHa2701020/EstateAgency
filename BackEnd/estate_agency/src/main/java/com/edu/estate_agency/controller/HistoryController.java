package com.edu.estate_agency.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.estate_agency.entity.History;
import com.edu.estate_agency.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HistoryController {
    @Autowired 
    private HistoryService historyService;
    @GetMapping("/customer/{id}")
    @Operation(summary = "Get History")
    public ResponseEntity<List<History>> getByCustomer(@PathVariable("id") Long id)
{
    List<History> od= historyService.getbyCustomer(id);
    return ResponseEntity.ok(od);
}
}
