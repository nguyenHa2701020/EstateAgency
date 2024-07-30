package com.edu.estate_agency.repository;

import com.edu.estate_agency.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("select p from Bill p where p.contract.id=?1")
    List<Bill> getByContractId(Long id);
    @Query("select p from Bill p where p.contract.id=?1 and p.status='Chưa thanh toán'")
    List<Bill> getByContractIDandNotPayment(Long id);
    @Query("SELECT MONTH(o.date) AS month, YEAR(o.date) AS year, SUM(o.total) AS total " +
            "FROM Bill o WHERE o.status = 'Đã thanh toán' AND o.contract.id IN :idList " +
            "GROUP BY YEAR(o.date), MONTH(o.date) " +
            "ORDER BY YEAR(o.date), MONTH(o.date)")
    public List<Object> getTotalByMonthYear(@Param("idList") List<Long> idList);

@Query("select p from Bill p where p.status='đã thanh toán' and p.contract.id IN:idList")
List<Bill> getPaymented(@Param("idList") List<Long> idList);
}