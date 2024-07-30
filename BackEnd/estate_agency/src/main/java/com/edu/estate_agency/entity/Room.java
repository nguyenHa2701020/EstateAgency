package com.edu.estate_agency.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private long id;
    @Column(name="name", unique=true)
    private String name;
    private Float price;
    private String address;
    private String description;
    private String state;
    @Lob
    @Column(columnDefinition="MEDIUMBLOB")
    private String img;
    private boolean enabled;

    @ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="user_id", referencedColumnName = "user_id")
private User user;
@JsonBackReference
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<Order> orders;

@JsonBackReference
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<History> histories;
@JsonBackReference
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<Contract> contracts;
@JsonBackReference
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<Comment> comments;
@JsonBackReference
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<Request> requests;
@JsonBackReference
@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
private List<Maintenance> maintenances;
}
