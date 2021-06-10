package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class MyUtils {
    @Autowired
    private HttpSession session;

    public int getLoginUserPk() {
        UserEntity loginUser = getLoginUser();
        return loginUser == null ? 0 : loginUser.getIuser();
    }

    public UserEntity getLoginUser() {
        return (UserEntity) session.getAttribute("loginUser");
    }
}
