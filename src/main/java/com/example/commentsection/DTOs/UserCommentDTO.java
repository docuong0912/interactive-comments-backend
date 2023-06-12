/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.commentsection.DTOs;



import com.example.commentsection.Comment.Comment;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class UserCommentDTO {
   private String username;
   private String content;
   private long id;
   private int score;
   private String replyTo;
   private List<Comment> reply;
   private String imageUrl;
   private Long period;

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
      this.period = period;
    }

    

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }


    public List<Comment> getReply() {
        return reply;
    }

    public void setReply(List<Comment> reply) {
        this.reply = reply;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public UserCommentDTO(String content) {
        this.content = content;
        this.score = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserCommentDTO(String username, String content, int score) {
        this.username = username;
        this.content = content;
        this.score = score;
    }

    public UserCommentDTO() {
        this.reply = new ArrayList<>();
    }
}
