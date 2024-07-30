package com.edu.estate_agency.entity;





import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="email",unique = true)
    private String email;

    private String fullname;



    private String password;

    

    private String state;

    private String address;

    private String phone;
@Lob
@Column(columnDefinition = "MEDIUMBLOB")
private String img;
@Column(name = "verification_code", length = 64)
    private String verificationCode;

    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Room> rooms;
    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Order> orders;
    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<History> histories;
    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonBackReference
    @OneToMany(mappedBy="firstUserName", cascade = CascadeType.ALL)
    private List<Chat> firstUserChats;

    @JsonBackReference
    @OneToMany(mappedBy="secondUserName", cascade = CascadeType.ALL)
    private List<Chat> secondUserNameChats;

    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Request> requests;

  
}
