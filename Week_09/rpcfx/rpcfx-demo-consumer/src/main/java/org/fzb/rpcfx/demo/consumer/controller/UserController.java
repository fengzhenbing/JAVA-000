package org.fzb.rpcfx.demo.consumer.controller;

import org.fzb.rpcfx.demo.api.User;
import org.fzb.rpcfx.demo.api.UserService;
import org.fzb.rpcfx.annotation.Referenced;
import org.fzb.rpcfx.client.Rpcfx;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author fengzhenbing
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Referenced
    private UserService userService;

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Integer id){
        UserService userService = Rpcfx.create(UserService.class,"");
        return userService.findById(id);
    }
}
