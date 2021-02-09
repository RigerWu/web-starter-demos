package com.rigerwu.web.swagger.controller;

import com.rigerwu.web.swagger.entity.Result;
import com.rigerwu.web.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * created by Riger on 2021/2/8
 */
@Slf4j
@RestController("/user")
@Api(tags = "UserController")
public class UserController {

    @ApiOperation(value = "注册用户", tags = "tag1")
    @PostMapping("/register")
    public Result<String> register(User user) {
        log.info(user.toString());
        return Result.ofSuccess("success");
    }

    @ApiOperation(value = "id获取用户", tags = "tag2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    @GetMapping("/getuser")
    public Result<User> getUserByName(@RequestParam("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("Tom");
        user.setAge(18);
        user.setGender(0);
        user.setAddr("ShangHai");
        return Result.ofSuccess(user);
    }

    @ApiOperation(value =  "修改密码")
    @PutMapping("/changepwd")
    public Result<User> changePassword(Long id, @RequestParam("password") String newpwd){
        log.info("change new password: {}", newpwd);
        User user = new User();
        user.setId(id);
        user.setUsername("Tom");
        user.setAge(18);
        user.setGender(0);
        user.setAddr(newpwd);
        return Result.ofSuccess(user);
    }

    @ApiOperation(value= "根据用户名删除用户")
    @DeleteMapping("/{username}/delete")
    public Result<String> deleteUserByName(@PathVariable("username") String username) {
        log.info("delete user: {}", username);
        return Result.ofSuccess("success");
    }
}
