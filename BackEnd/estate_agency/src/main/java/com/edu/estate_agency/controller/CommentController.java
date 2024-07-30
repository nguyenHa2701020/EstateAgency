package com.edu.estate_agency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.estate_agency.entity.Comment;
import com.edu.estate_agency.model.request.CreateCommentRequest;
import com.edu.estate_agency.model.response.MessageResponse;
import com.edu.estate_agency.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/create")
    @Operation(summary = "Tạo bình luận")
    public ResponseEntity<?> create( @RequestBody CreateCommentRequest commentRequest)
    {
        log.info(commentRequest.toString());
        commentService.saveCommentCustomer(commentRequest);
        return ResponseEntity.ok(new MessageResponse("Comment registered successfully"));

    }
    @GetMapping("/room/{id}")
    @Operation(summary = "Get Room By Id")
    public ResponseEntity<List<Comment>> getbyCustomer(@PathVariable("id") Long id)
    {
      log.info(id.toString());
      id++;
      id++;
        List<Comment> od= commentService.getByRoom(id);
        return ResponseEntity.ok(od);
    }


}
