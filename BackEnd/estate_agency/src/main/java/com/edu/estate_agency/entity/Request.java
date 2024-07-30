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
@Table(name="request")
public class    Request {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String status;
    private String description;
    private Boolean browse;
    private Date date;
 @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="user_id", referencedColumnName = "user_id")
private User user;
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="room_id", referencedColumnName = "room_id")
private Room room;
}
