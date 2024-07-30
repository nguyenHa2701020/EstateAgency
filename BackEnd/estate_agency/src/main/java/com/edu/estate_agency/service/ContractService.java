package com.edu.estate_agency.service;

import com.edu.estate_agency.entity.Contract;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContractService {
    List<Contract> getByAgent(Long id);
    List<Contract> getByRoom(Long id);
    Contract upFile(Long id, MultipartFile file);
    byte[] getFile(Long id);
}
