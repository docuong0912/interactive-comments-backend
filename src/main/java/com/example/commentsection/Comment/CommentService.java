/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.commentsection.Comment;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository pos;
    public void addComment(Comment comment){
        pos.save(comment);
    }
    public Optional<Comment> findSingleComment(long id){
        return pos.findById(id);
    }

    @Transactional
    public int updateScore(long id,boolean isPlus,int prevScore){
        Comment comment =pos.findById(id).orElse(null);
        int vote;
        int score = comment.getScore();
        if(isPlus) vote=1;
        else vote=-1;
        vote-=prevScore;
        score+=vote;
        comment.setScore(score);
        return score;
    }
    @Transactional
    public Comment updateComment(long id,String content){
        Comment comment =pos.findById(id).orElse(null);
        comment.setContent(content);
        return comment;
    }
    public void deleteComment(long id){
        pos.deleteById(id);
    }
    public List<Comment> getAllComments(){
        List<Comment> comments = pos.findByParentCommentIsNull();
        for (Comment comment : comments) {
            List<Comment> replies = new ArrayList<>();
            retrieveReplies(comment, replies);
            comment.setReplies(replies);
        }   
        return comments;
    }
   

private void retrieveReplies(Comment comment, List<Comment> replies) {
    List<Comment> directReplies = pos.findByParentComment(comment);
    for (Comment reply : directReplies) {
        replies.add(reply);
        retrieveReplies(reply, replies);
        
    }
 
}

}
