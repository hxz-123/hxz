package com.abc.controller;
import com.abc.bean.Form;
import com.abc.bean.Params;
import com.abc.bean.Result;
import com.abc.bean.User;
import com.abc.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result save(@RequestBody Form form){
        if (form.getId() == null){
            userService.add(form);
        }else {
            userService.update(form);
        }
        return Result.success();
    }
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<User> info = userService.findBySearch(params);
        return Result.success(info);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.delete(id);
        return Result.success();
    }
    @PostMapping("/login")
    public Result login(@RequestBody Form form){
        User loginUser = userService.login(form);
        return  Result.success(loginUser);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Form form){

        return Result.success(userService.register(form));
    }
}
