package com.edu.estate_agency.controller;

import com.edu.estate_agency.entity.Request;
import com.edu.estate_agency.model.request.CreateRequestRequest;
import com.edu.estate_agency.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/request")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class RequestController {
    @Autowired
    private RequestService requestService;

   @GetMapping("/all/{id}")
    @Operation(summary = "Get Request Id")
    public ResponseEntity<?> getAll(@PathVariable long id, @RequestParam("filter") String filter)
   {
       List<Request> requests= requestService.getAllByUser(id, filter);
       return ResponseEntity.ok(requests);
   }
   @PostMapping("/save")
    @Operation(summary = "get request save")
    public  ResponseEntity<Request> postMethodName(@RequestBody CreateRequestRequest createRequestRequest){
       log.info("Hell"+createRequestRequest.toString());
       return ResponseEntity.ok(requestService.save(createRequestRequest));
   }
    @PatchMapping("/status/id/{id}/status/{status}")
    @Operation(summary = "get request save")
    public  ResponseEntity<Request> patchMethodName(@PathVariable("id") long id, @PathVariable("status") String status ){
        log.info(id+ status);
        return ResponseEntity.ok(requestService.updateStatus(id, status));
    }
}
