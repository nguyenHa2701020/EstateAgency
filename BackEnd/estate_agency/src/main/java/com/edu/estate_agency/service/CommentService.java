package com.edu.estate_agency.service;

import java.util.List;

import com.edu.estate_agency.entity.Comment;
import com.edu.estate_agency.model.request.CreateCommentRequest;

public interface CommentService {
Comment saveCommentCustomer(CreateCommentRequest commentRequest);
List<Comment> getByRoom(Long id);

    
}
