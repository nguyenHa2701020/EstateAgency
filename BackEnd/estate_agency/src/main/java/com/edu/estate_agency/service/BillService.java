package com.edu.estate_agency.service;

import com.edu.estate_agency.entity.Bill;
import com.edu.estate_agency.model.request.CreateBillRequest;

import java.util.List;
import java.util.Map;

public interface BillService {
    Bill save(CreateBillRequest createBillRequest);
    List<Bill> getByIdContract(Long id);
    byte[] generateReport(long id);
    Bill detail(long id);
    Bill updatePayment(long id, String user);
    List<Object> getReport(Long idAgent);
    List<Integer> getReportRoomandMaintain(Long idAgent);
    Map<String, Integer> getReportAgent(Long id);
    Map<String, Integer> getReportAdmin();
}
