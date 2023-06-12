/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.commentsection.DTOs;



import com.example.commentsection.Comment.Comment;
import com.example.commentsection.Users.Users;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Component;

/**
 *
 * @author Asus
 */
@Component
public class Mapper {
    
    public UserCommentDTO toDto(Comment comment){
        UserCommentDTO dto = new UserCommentDTO();
        dto.setUsername(comment.getUser().getUsername());
        dto.setContent(comment.getContent());
        dto.setReply(comment.getReplies());
        dto.setScore(comment.getScore());
        dto.setId(comment.getId());
        dto.setReplyTo(comment.getReplyTo());
        dto.setImageUrl(comment.getUser().getImageUrl());
        dto.setPeriod(comment.getPeriod());
        return dto;
     
    }
    public UserCommentDTO toDto(Users user){
        UserCommentDTO dto = new UserCommentDTO();
        dto.setUsername(user.getUsername());
        dto.setId(user.getId());
        
        
        return dto;
     
    }
    public Users toUser(UserCommentDTO dto){
        return new Users(dto.getUsername());
    }
    public Comment toComment(UserCommentDTO dto){
       
        Comment comment = new Comment(dto.getContent());
        comment.setCreateAt(LocalDateTime.now());
     
        return comment;
    }

}
