package com.consumer.controller;

import com.consumer.entity.CommentResult;
import com.consumer.entity.User;
import com.consumer.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/hello")
    public String hello(){

        return userFeignService.Hello();
    }

    @GetMapping("/addCart/{userId}")
    public CommentResult<User> addCart(@PathVariable Integer userId){

        CommentResult<User> list = userFeignService.getUserById(userId);

        return list;

    }

    //增加
    @PostMapping("/restful.post")
    public CommentResult<User> methodPost(Integer userId, String name){
        CommentResult<User> list = userFeignService.methodPost(userId, name);

        return list;
    }

    //修改
    @RequestMapping("/restful.put")
    public CommentResult<User> methodPut(Integer userId, String name, String changeName){
        CommentResult<User> list = userFeignService.methodPut(userId, name, changeName);

        return list;
    }

    //删除
    @DeleteMapping("/restful.delete/{userId}")
    public CommentResult<User> methodDelete(@PathVariable Integer userId){
        CommentResult<User> list = userFeignService.methodDelete(userId);

        return list;
    }
}
