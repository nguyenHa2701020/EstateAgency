package com.edu.estate_agency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.estate_agency.entity.History;
import com.edu.estate_agency.repository.HistoryReposity;
import com.edu.estate_agency.service.HistoryService;
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
private HistoryReposity historyReposity;
    @Override
    public List<History> getbyCustomer(Long id) {
       return historyReposity.getbyCustomer(id);
    }
    
}
