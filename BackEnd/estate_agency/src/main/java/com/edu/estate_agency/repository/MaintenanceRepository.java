package com.edu.estate_agency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.estate_agency.entity.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    @Query("select p from Maintenance p where p.room.id=?1")
    List<Maintenance> getListByRoom(Long id);

    @Query("select p from Maintenance p where p.room.user.id=?1")
    List<Maintenance> getLstAgent(Long id);

}
