/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.commentsection.Comment;

import com.example.commentsection.Users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;
    private int score;
    private String username;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @ManyToOne
    @JoinColumn(name = "uid",referencedColumnName = "id")
    @JsonBackReference
    private Users user;
    private String replyTo;

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    @ManyToOne
    @JoinColumn(name = "parent_comment_id",referencedColumnName = "id")
    @JsonBackReference(value="list_of_reply")
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "list_of_reply")
    @JsonIgnore
    private List<Comment> replies;

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }
    
    private LocalDateTime createAt;
    @Transient
    private Duration period;

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public long getPeriod() {
        return Duration.between(createAt,LocalDateTime.now()).getSeconds() ;
    }

    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    public Comment() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Comment(String content,Users user) {
        this.content = content;
        this.score = 0;
        this.replies = new ArrayList<>();
        this.user =user;
        this.username = user.getUsername();
    }

    public Comment(String content, int score, Users user, List<Comment> reply) {
        this.content = content;
        this.score = score;
        this.user = user;

    }

    public String getContent() {
        return content;
    }

    public Comment(String content) {
        this.content = content;
        this.score = 0;
        this.replies = new ArrayList<>();
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


}
