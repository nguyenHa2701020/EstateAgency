package com.edu.estate_agency.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.estate_agency.entity.Comment;
import com.edu.estate_agency.model.request.CreateCommentRequest;
import com.edu.estate_agency.repository.CommentRepository;
import com.edu.estate_agency.repository.RoomRepository;
import com.edu.estate_agency.repository.UserRepository;
import com.edu.estate_agency.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment saveCommentCustomer(CreateCommentRequest commentRequest) {
        Comment cmt = new Comment();
        cmt.setContent(commentRequest.getContent());
        cmt.setDate(new Date());
        cmt.setRoom(roomRepository.findById(commentRequest.getIdRoom()).get());
        cmt.setUser(userRepository.findById(commentRequest.getIdCustomer()).get());
        return commentRepository.save(cmt);

    }

    @Override
    public List<Comment> getByRoom(Long id) {
        return commentRepository.getByRoom(id);
    }

}
