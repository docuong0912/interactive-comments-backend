/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.commentsection.Users;

import com.example.commentsection.Comment.Comment;
import com.example.commentsection.Comment.CommentRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Asus
 */
@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner user(UserRepository res){
        return args->{
        Users u1 = new Users("juliusomo");
        Users u2 = new Users("amyrobson");
        Users u3 = new Users("maxblagun");
        Users u4 = new Users("ramsesmiron");
        Comment c1 = new Comment("Impressive! Though it seems the drag feature could be improved. But overall it looks incredible. You've nailed the design and the responsiveness at various breakpoints works really well.",u2);
        Comment r1 = new Comment("If you're still new, I'd recommend focusing on the fundamentals of HTML, CSS, and JS before considering React. It's very tempting to jump ahead but lay a solid foundation first.",u4);
        
        Comment c2 = new Comment("Woah, your project looks awesome! How long have you been coding for? I'm still new, but think I want to dive into React as well soon. Perhaps you can give me an insight on where I can learn React? Thanks!",u3);
        Comment r2 = new Comment("I couldn't agree more with this. Everything moves so fast and it always seems like everyone knows the newest library/framework. But the fundamentals are what stay constant.",u1);
        c1.setCreateAt(LocalDateTime.of(LocalDate.of(2023,6,3),LocalTime.of(10,0,0)));
        c2.setCreateAt(LocalDateTime.of(LocalDate.of(2023,5,3),LocalTime.of(15,0,0)));
        r1.setCreateAt(LocalDateTime.of(LocalDate.of(2023,5,25),LocalTime.of(19,0,0)));
        r2.setCreateAt(LocalDateTime.of(LocalDate.of(2023,6,8),LocalTime.of(8,0,0)));
        c2.getReplies().add(r1);
        r1.getReplies().add(r2);
        r2.setParentComment(r1);
        r1.setParentComment(c2);
        r2.setReplyTo(r1.getUser().getUsername());
        r1.setReplyTo(c2.getUser().getUsername());
        
        
        u1.getComment().add(r2);
        u2.getComment().add(c1);
        u3.getComment().add(c2);
        u4.getComment().add(r1);
        u1.setImageUrl("/images/avatars/image-juliusomo.png");
        u2.setImageUrl("/images/avatars/image-amyrobson.png");
        u3.setImageUrl("/images/avatars/image-maxblagun.png");
        u4.setImageUrl("/images/avatars/image-ramsesmiron.png");
        r1.setImageUrl(u4.getImageUrl());
        r2.setImageUrl(u1.getImageUrl());
        res.saveAll(List.of(u1,u2,u3,u4));
        
    };
    }
    

}
