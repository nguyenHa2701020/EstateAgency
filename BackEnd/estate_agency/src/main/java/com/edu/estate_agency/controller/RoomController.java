package com.edu.estate_agency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.estate_agency.entity.Room;
import com.edu.estate_agency.model.request.CreateRoomRequest;
import com.edu.estate_agency.model.response.MessageResponse;
import com.edu.estate_agency.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/room")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    @Operation(summary = "Tạo phòng ")
    public ResponseEntity<?> create(@RequestParam("name") String name, @RequestParam("price") String price,
            @RequestParam("address") String address, @RequestParam("describe") String describe,
            @RequestParam("state") String state, @RequestParam("img") MultipartFile img,
            @RequestParam("idAgent") String idAgent) {

        // CreateRoomRequest.builder().name(name).address(address).describe(describe).state(state).build();
        CreateRoomRequest request = new CreateRoomRequest();
        request.setName(name);
        request.setAddress(address);
        request.setDescribe(describe);
        request.setState(state);
        try {
            if (price != null) {
                request.setPrice(Float.parseFloat(price));

            }
            if (idAgent != null) {
                request.setIdAgent(Long.parseLong(idAgent));
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid price or idAgent."));

        }
        log.info("Hello" + request.toString());
        request.setImg(img);
        roomService.createRoom(request);
        return ResponseEntity.ok(new MessageResponse("Room registered successfully!"));

    }

    @PutMapping("/update")
    @Operation(summary = "Sửa phòng")
    public ResponseEntity<?> update(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("price") String price,
            @RequestParam("address") String address, @RequestParam("describe") String describe,
            @RequestParam("state") String state, @RequestParam("img") MultipartFile img,
            @RequestParam("idAgent") String idAgent) {
        CreateRoomRequest request = new CreateRoomRequest();
        
        request.setName(name);
        request.setAddress(address);
        request.setDescribe(describe);
        request.setState(state);
        try { 
            if (price != null) {
                request.setPrice(Float.parseFloat(price));

            }
            if (idAgent != null) {
                request.setIdAgent(Long.parseLong(idAgent));
            }
            if(id!=null)
            {
                request.setId(Long.parseLong(id));
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid price or idAgent."));

        }
      //  log.info("Hello" + request.toString());
        request.setImg(img);
        roomService.updateRoom(request);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    // roomService.delete(id);
    // return ResponseEntity.ok().build();
    // }

    @GetMapping("/")
    @Operation(summary = "All room")
    public ResponseEntity<List<Room>> getAll() {
        List<Room> all = roomService.getAll();
        return ResponseEntity.ok(all);

    }

    @GetMapping("/allenable")
    @Operation(summary = "All Room enable")
    public ResponseEntity<List<Room>> getAllEnable() {
    List<Room> all = roomService.getbyAllEnable();
    return ResponseEntity.ok(all);

    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "Detail Room")
    public ResponseEntity<Room> getRoomId(@PathVariable("id") Long id) {
    Room all = roomService.detailRoom(id);
    return ResponseEntity.ok(all);
    }
//lấy phòng theo id chủ phòng :))
    @GetMapping("/all/{id}")
    @Operation(summary = "All Room id")
    public ResponseEntity<List<Room>> getAgenRoom(@PathVariable("id") Long id) {
        List<Room> all = roomService.getbyAgent(id);
        return ResponseEntity.ok(all);

    }
    @GetMapping("/allenableAgent/{id}")
    @Operation(summary= "All Room agent id")
    public ResponseEntity<List<Room>> getAgenRoomEnable(@PathVariable("id") Long
    id)
    {
    List<Room> all= roomService.getbyAgentEnable(id);
    return ResponseEntity.ok(all);

    }
    @PostMapping("/enable/{id}")
    @Operation(summary = "Enable Room")
    public ResponseEntity<Room> enable(@PathVariable("id") Long id, @RequestParam
    Boolean check)
    {
    return ResponseEntity.ok(roomService.enable(id, check));
    }

}