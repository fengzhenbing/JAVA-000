package service;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: fzb
 * @create: 2020-11-18 13:54
 */
@Service
public class LearnServiceImpl implements LearnService{

    @Override
    public void learn() {
        System.out.println("start learning");
    }
}
