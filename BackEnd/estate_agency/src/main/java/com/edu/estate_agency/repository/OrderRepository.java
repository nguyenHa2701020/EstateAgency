package com.edu.estate_agency.repository;

import com.edu.estate_agency.entity.Order;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository< Order, Long> {
    @Query("select p from Order p where p.user.id=?1")
    List<Order> getLstByCustomer(Long id);
    @Query("select p from Order p where p.room.id=?1")
    List<Order> getLstByRoom(Long id);

}
