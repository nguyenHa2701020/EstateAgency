package com.edu.estate_agency.service.impl;

import com.edu.estate_agency.entity.Request;
import com.edu.estate_agency.model.request.CreateRequestRequest;
import com.edu.estate_agency.repository.RequestRepository;
import com.edu.estate_agency.repository.RoomRepository;
import com.edu.estate_agency.repository.UserRepository;
import com.edu.estate_agency.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
@Autowired
private RequestRepository requestRepository;
@Autowired
private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public List<Request> getAllByUser(Long id, String filter) {
        return null;
    }

    @Override
    public Request save(CreateRequestRequest createRequestRequest) {
      Request request= new Request();
      request.setDate(new Date());
      request.setDescription(createRequestRequest.getDescription());
      request.setStatus(createRequestRequest.getStatus());
      request.setRoom(roomRepository.findById(createRequestRequest.getIdroom()).get());
        request.setUser(userRepository.findById(createRequestRequest.getIduser()).get());


       return requestRepository.save(request);
    }

    @Override
    public Request updateStatus(long id, String status) {
        Request request= requestRepository.findById(id).get();
        request.setStatus(status);

        return requestRepository.save(request);
    }
}
