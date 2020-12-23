package org.fzb.rpcfx.demo.consumer.controller;

import api.User;
import api.UserService;
import org.fzb.rpcfx.annotation.Reference;
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

    @Reference
    private UserService userService;

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Integer id){
        return userService.findById(id);
    }
}
