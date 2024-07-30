package com.edu.estate_agency.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bill")
public class Bill {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;
    private String name;
    private Date date;
    private long total;
    private String user;
    private String status;
     @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="contracts_id", referencedColumnName = "contracts_id")
private Contract contract;
    
}
