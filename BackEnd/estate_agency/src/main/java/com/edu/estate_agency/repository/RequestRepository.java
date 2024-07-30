package com.edu.estate_agency.repository;

import com.edu.estate_agency.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("select p from Request p where p.room.id=?1 ")
    List<Request> getByRoom(Long id);
    @Query("select p from Request p where p.user.id=?1 ")
    List<Request> getByUser(Long id);


}
