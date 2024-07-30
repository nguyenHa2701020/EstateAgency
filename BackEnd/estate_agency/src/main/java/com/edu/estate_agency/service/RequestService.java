package com.edu.estate_agency.service;

import com.edu.estate_agency.entity.Request;
import com.edu.estate_agency.model.request.CreateRequestRequest;

import java.util.List;

public interface RequestService {
List<Request> getAllByUser(Long id, String filter);
Request save(CreateRequestRequest createRequestRequest);
Request updateStatus(long id, String status);


}
