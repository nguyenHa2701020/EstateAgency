package com.edu.estate_agency.entity;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="chat")
public class Chat {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_id")
    private long id;


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="first_user_id", referencedColumnName = "user_id")
private User firstUserName;
@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", referencedColumnName = "user_id")
    private User secondUserName;

   
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messageList;
}


    

