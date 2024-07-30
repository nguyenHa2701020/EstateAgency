package com.edu.estate_agency.repository;

import com.edu.estate_agency.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository< Contract, Long> {
    @Query("select p from Contract  p where p.user.id=?1")
    List<Contract> getByAgent(Long id);
    @Query("select p from Contract  p where p.room.id=?1")
    List<Contract> getByRoom(Long id);


}
