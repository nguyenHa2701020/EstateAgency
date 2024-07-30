package com.edu.estate_agency.service;

import java.util.List;

import com.edu.estate_agency.entity.Room;
import com.edu.estate_agency.model.request.CreateRoomRequest;

public interface RoomService {
    Room createRoom(CreateRoomRequest createRoomRequest);
     Room updateRoom(CreateRoomRequest createRoomRequest);
     List<Room> getAll();
      List<Room> getbyAgent(Long idAgent);
      Room detailRoom(Long id);
      Room enable(Long id, Boolean chBoolean);
     List<Room> getbyAgentEnable(Long idAgent);
     List<Room> getbyAllEnable();
    // void delete(Long id);
    

    
}
