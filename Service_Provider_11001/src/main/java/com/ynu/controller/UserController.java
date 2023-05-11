package com.ynu.controller;

import com.ynu.entity.CommentResult;
import com.ynu.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/hello")
    public String Hello(){
        return "Hello";
    }

    @GetMapping("/getUserById/{userId}")
    public CommentResult<User> getUserById(@PathVariable Integer userId){
        User u = new User(userId,"小明","123456");
        return new CommentResult<>(200,"success(11001) 成功获取用户信息！",u);
    }

    @PostMapping("/restful.post")
    public CommentResult<User> methodPost(@RequestParam Integer userId, @RequestParam String name){
        User u = new User(userId,name,"123456");
        return new CommentResult<>(200,"success(11001) 成功添加用户信息！",u);
    }

    @PutMapping("/restful.put")
    public CommentResult<User> methodPut(@RequestParam Integer userId, @RequestParam String name, @RequestParam String changeName){
        User u = new User(userId,name,"123456");
        u.setUserName(changeName);
        return new CommentResult<>(200,"success(11001) 成功修改用户信息！",u);
    }

    @DeleteMapping("/restful.delete/{userId}")
    public CommentResult<User> methodDelete(@PathVariable Integer userId){
        User u = new User(userId,"张三","123456");

        return new CommentResult<>(200,"success(11001) 成功删除用户信息！",u);
    }

}
