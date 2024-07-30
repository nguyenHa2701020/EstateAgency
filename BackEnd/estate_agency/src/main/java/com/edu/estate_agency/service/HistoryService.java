package com.edu.estate_agency.service;

import java.util.List;

import com.edu.estate_agency.entity.History;

public interface HistoryService {
    List<History> getbyCustomer(Long id);
    
}
