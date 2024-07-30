package com.edu.estate_agency.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.estate_agency.entity.Room;
import com.edu.estate_agency.entity.User;
import com.edu.estate_agency.exception.NotFoundException;
import com.edu.estate_agency.model.request.CreateRoomRequest;
import com.edu.estate_agency.repository.RoomRepository;
import com.edu.estate_agency.repository.UserRepository;
import com.edu.estate_agency.service.RoomService;
import com.edu.estate_agency.utils.ImageUpload;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

   
    private static  ImageUpload imgUpload;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Room createRoom(CreateRoomRequest createRoomRequest) {
        Room room = new Room();
        room.setName(createRoomRequest.getName());
        room.setPrice(createRoomRequest.getPrice());
        room.setAddress(createRoomRequest.getAddress());
        room.setDescription(createRoomRequest.getDescribe());
        room.setState("Chưa thuê");
        User user = userRepository.findById(createRoomRequest.getIdAgent()).orElseThrow(
                () -> new NotFoundException("Not Found Category With Id: " + createRoomRequest.getIdAgent()));
        room.setUser(user);
        try {
            if (createRoomRequest.getImg() == null) {
                room.setImg(null);

            } else {
                imgUpload.uploadFile(createRoomRequest.getImg());
                room.setImg(Base64.getEncoder().encodeToString(createRoomRequest.getImg().getBytes()));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return roomRepository.save(room);

    }

    @Override
    public Room updateRoom(CreateRoomRequest createRoomRequest) {
    Room room = roomRepository.findById(createRoomRequest.getId()).get();
   // log.info(room.toString());
    room.setName(createRoomRequest.getName());
    room.setPrice(createRoomRequest.getPrice());
    room.setAddress(createRoomRequest.getAddress());
    room.setDescription(createRoomRequest.getDescribe());
    room.setState("Chưa thuê");
    User user = userRepository.findById(createRoomRequest.getIdAgent()).orElseThrow(
        () -> new NotFoundException("Not Found Category With Id: " + createRoomRequest.getIdAgent()));
room.setUser(user);
try {
    if (createRoomRequest.getImg() == null) {
        room.setImg(null);

    } else {
        imgUpload.uploadFile(createRoomRequest.getImg());
        room.setImg(Base64.getEncoder().encodeToString(createRoomRequest.getImg().getBytes()));
    }
} catch (Exception e) {
    // TODO: handle exception
}
return roomRepository.save(room);

    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    @Override
    public List<Room> getbyAgent(Long idAgent) {
       return roomRepository.getByAgent(idAgent);
    }

    @Override
    public Room detailRoom(Long id) {
       return roomRepository.findById(id).get();
    }


    @Override
    public Room enable(Long id, Boolean chBoolean) {
      Room room= roomRepository.findById(id).get();
      room.setEnabled(chBoolean);
      return roomRepository.save(room);
    }

    @Override
    public List<Room> getbyAgentEnable(Long idAgent) {
        return roomRepository.getByAgentEnable(idAgent);
    }

    @Override
    public List<Room> getbyAllEnable() {
       return roomRepository.getAllEnable();
    }

    // @Override
    // public void delete(Long id) {
    //    Room room= roomRepository.findById(id).get();
    //    roomRepository.delete(room);
    // }

}
