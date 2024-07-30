package com.edu.estate_agency.controller;

import com.edu.estate_agency.entity.Order;
import com.edu.estate_agency.model.request.CreateOrderRequest;
import com.edu.estate_agency.model.response.MessageResponse;
import com.edu.estate_agency.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/create")
    @Operation(summary = "Tạo phòng")
    public ResponseEntity<?> create(@Valid @RequestBody CreateOrderRequest createOrderRequest)

    {
        orderService.saveOrders(createOrderRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));

    }
    @GetMapping("/customer/{id}")
    @Operation(summary = "get Order customer")
    public ResponseEntity<List<Order>> getbyCustomer(@PathVariable("id") Long id) {
        List<Order> od = orderService.getOrderCustomerss(id);
        return ResponseEntity.ok(od);
    }

    @GetMapping("/room/{id}")
    @Operation(summary = "get Order room")
    public ResponseEntity<List<Order>> getbyRoom(@PathVariable("id") Long id) {
        List<Order> od = orderService.getOrderRooms(id);
        return ResponseEntity.ok(od);
    }

    @PatchMapping("/id/{id}/browse/{browse}")
    @Operation(summary = "get Order browser")
    public ResponseEntity<?> updatebrowse(@PathVariable("id") Long id, @PathVariable("browse") String browse) {
        orderService.updateBrowse(id, browse);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
