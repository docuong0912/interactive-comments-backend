
package com.example.commentsection.Users;


import com.example.commentsection.Comment.Comment;
import com.example.commentsection.Comment.CommentService;
import com.example.commentsection.DTOs.Mapper;
import com.example.commentsection.DTOs.UserCommentDTO;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserService service;
    @Autowired
    private CommentService cservice;
    @GetMapping
    public List<UserCommentDTO> getUser(){
        return cservice.getAllComments().stream().map(mapper::toDto).collect(toList());
    }
    @GetMapping("/username/{name}")
    public Users findByUserName(@PathVariable String name){
        return service.findByUsername(name);
    }
     @GetMapping("/all")
    public List<Users> findAll(){
        return service.findAllUser();
    }
    @GetMapping("/{id}")
    public UserCommentDTO getSingleComment(@PathVariable long id){
        return mapper.toDto(cservice.findSingleComment(id).orElse(null));
    }
    @PostMapping("/add")
    public Users addUser(@RequestBody UserCommentDTO dto){
        Users user = new Users();
        List<Comment> comments = new ArrayList<>();
        user.setUsername(dto.getUsername());
        user.setComment(comments);
        service.addUser(user);
        return user;
    }
    @PostMapping("/comment/add")
    public Comment addComment(@RequestBody UserCommentDTO dto){
        Users user = service.findSingleUSer(1).orElse(null);
        Comment comment = mapper.toComment(dto);
        comment.setUser(user);
        comment.setParentComment(null);
        
        comment.setUsername(user.getUsername());
        user.getComment().add(comment);
        service.addUser(user);
        return comment;
    }
    @PostMapping("/comment/reply/{id}")
    public Comment addReply(@RequestBody UserCommentDTO dto,@PathVariable long id){
        Comment comment = cservice.findSingleComment(id).orElse(null);
        Users user = service.findSingleUSer(1).orElse(null);
        Comment reply = mapper.toComment(dto);
        reply.setUser(user);
        reply.setUsername(user.getUsername());
        reply.setReplyTo(comment.getUser().getUsername());
        comment.getReplies().add(reply);
        reply.setParentComment(comment);
        user.getComment().add(reply);
        reply.setImageUrl(reply.getUser().getImageUrl());
        service.addUser(user);
       
        return reply;
    }
    @PutMapping("/updateScore/{id}")
    public int updatScore(@PathVariable long id,@RequestParam boolean isPlus,@RequestParam int prevScore){
        return cservice.updateScore(id, isPlus,prevScore);
    }
    @PutMapping("/updateComment/{id}")
    public Comment updateComment(@PathVariable long id,@RequestParam String body){
        return cservice.updateComment(id, body);
    }
    @DeleteMapping("/comment/delete/{id}")
    public void deleteComment(@PathVariable long id){
        cservice.deleteComment(id);
    }
}
