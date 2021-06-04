package com.koreait.spring.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);
        if(result == null) { //아이디 없음
            return "/user/login?err=1";
        } else if(BCrypt.checkpw(param.getUpw(), result.getUpw())) { //로그인 성공
//          세션처리 추가
            result.setUpw(null);
            session.setAttribute("loginUser", result);
            return "/board/list";
        } else { //비밀번호 틀림
            return "/user/login?err=2";
        }
    }

    public int join(UserEntity param) {
//      암호화 구현
        String cryptPw = BCrypt.hashpw(param.getUpw(), BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }

}
