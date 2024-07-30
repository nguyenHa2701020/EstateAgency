package com.edu.estate_agency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.estate_agency.entity.History;
@Repository
public interface HistoryReposity extends JpaRepository<History, Long> {
@Query("select p from History p where p.user.id=?1")
List<History> getbyCustomer(Long id);
    
}
