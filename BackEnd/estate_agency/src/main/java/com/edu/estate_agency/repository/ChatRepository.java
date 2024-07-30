package com.edu.estate_agency.repository;

import com.edu.estate_agency.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("select p from Chat p where p.firstUserName.username=?1 ")
    HashSet<Chat> getChatByFirstUserName(String username);

    @Query("select p from Chat p where p.secondUserName.username=?1 ")
    HashSet<Chat> getChatBySecondUserName(String username);

    @Query("select p from Chat p where p.firstUserName.username=?1 AND p.secondUserName.username=?2 ")
    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstname, String secondname);

    @Query("select p from Chat p where p.secondUserName.username=?1 AND p.firstUserName.username=?2 ")
    HashSet<Chat> getChatBySecondUserNameAndFirstUserName(String secondname, String firstname);

}