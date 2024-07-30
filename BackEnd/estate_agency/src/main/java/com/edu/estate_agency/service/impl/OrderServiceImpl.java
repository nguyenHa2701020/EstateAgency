package com.edu.estate_agency.service.impl;

import com.edu.estate_agency.entity.Contract;
import com.edu.estate_agency.entity.History;
import com.edu.estate_agency.entity.Order;
import com.edu.estate_agency.entity.Room;
import com.edu.estate_agency.model.request.CreateOrderRequest;
import com.edu.estate_agency.repository.*;
import com.edu.estate_agency.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
@Autowired
private HistoryReposity historyReposity;
@Autowired
private ContractRepository contractRepository;
    @Override
    public Order saveOrders(CreateOrderRequest createOrderRequest) {
        Order order= new Order();
        order.setRoom(roomRepository.findById(createOrderRequest.getIdRoom()).get());
        order.setUser(userRepository.findById(createOrderRequest.getIdAgent()).get());
     order.setStatus("Chờ phản hồi");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderCustomerss(Long id) {
        List<Order> list= orderRepository.getLstByCustomer(id);
        return list;
    }

    @Override
    public List<Order> getOrderRooms(Long id) {
       List<Order> lst= orderRepository.getLstByRoom(id);
       return  lst;
    }

    @Override
    public Order updateBrowse(Long id, String browse) {
        Order order=orderRepository.findById(id).get();
        if(browse.equals("Duyệt")) {
            History history = new History();
            history.setUser(order.getUser());
            history.setRoom(order.getRoom());
            history.setStatus("Đang thuê");
            historyReposity.save(history);
            Contract contract = new Contract();
            contract.setName("Hợp đồng nhà" + order.getRoom().getName());
            contract.setAcount(order.getUser().getUsername());
            contract.setStart(new Date());
            contract.setUser(order.getRoom().getUser());
            contract.setRoom(order.getRoom());
            contractRepository.save(contract);
            Room room = roomRepository.findById(order.getRoom().getId()).get();
            room.setState("Đã thuê");
            room.setEnabled(false);
            roomRepository.save(room);
        }
        order.setStatus(browse);
        order.setBrowse(true);
        return orderRepository.save(order);


    }
}
