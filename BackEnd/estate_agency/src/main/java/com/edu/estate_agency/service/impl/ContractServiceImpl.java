package com.edu.estate_agency.service.impl;

import com.edu.estate_agency.entity.Contract;
import com.edu.estate_agency.repository.ContractRepository;
import com.edu.estate_agency.repository.RoomRepository;
import com.edu.estate_agency.repository.UserRepository;
import com.edu.estate_agency.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
@Service
@Slf4j
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Contract> getByAgent(Long id) {
        return contractRepository.getByAgent(id);
    }

    @Override
    public List<Contract> getByRoom(Long id) {
        return contractRepository.getByRoom(id);
    }

    @Override
    public Contract upFile(Long id, MultipartFile file) {
        Contract contract= contractRepository.findById(id).get();

        try{
            contract.setFile(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        catch (Exception e){

        }
        return contractRepository.save(contract);
    }

    @Override
    public byte[] getFile(Long id) {
        Contract contract= contractRepository.findById(id).get();
        byte[] decodeBytes= Base64.getDecoder().decode(contract.getFile());
        return decodeBytes;

    }
}
