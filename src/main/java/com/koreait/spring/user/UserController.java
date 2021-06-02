package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//servlet으로부터 연결을 받을 수 있음
@Controller
@RequestMapping("/user") // mapping 설정 (1차 주소 값)
public class UserController {   // 상위 /user 주소값을 줬다면 login, join만 적어도 됨

    @Autowired // 자동 연결
    private UserService service; // UserService에 bean등록만 해뒀다면 service에 값이 들어감

    @RequestMapping(value = "/login") // method도 원래 추가해야 하지만 get방식은 디폴트, 생략 가능
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/join")
    public String join() { return "user/join"; }

    @RequestMapping(value = "/join", method = RequestMethod.POST) // post방식은 method설정
        public String join(UserEntity param) {
        service.join(param);
//            System.out.println("uid" + param);
        return "redirect:/user/login";
    }

}
