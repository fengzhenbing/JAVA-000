package org.fzb.rpcfx.demo.provider.service;

import org.fzb.rpcfx.demo.api.User;
import org.fzb.rpcfx.demo.api.UserService;
import org.fzb.rpcfx.annotation.Service;

/**
 * UserServiceImpl
 *
 * @author fengzhenbing
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return null;
    }
}
