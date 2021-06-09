package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//servlet으로부터 연결을 받을 수 있음
@Controller
@RequestMapping("/user") // mapping 설정 (1차 주소 값)
public class UserController {   // 상위 /user 주소값을 줬다면 login, join만 적어도 됨

    @Autowired // 자동 연결
    private UserService service; // UserService에 bean등록만 해뒀다면 service에 값이 들어감

    //  로그인 에러 처리               int형 값이 들어와야 err이 잡힘
    //  Model 활용
    @RequestMapping(value = "/login")
    public String login(Model model, @RequestParam(value = "err", defaultValue = "0") int err) {
        switch (err) {
            case 1: //아이디 없음
                model.addAttribute("errMsg", "아이디를 확인해 주세요.");
                break;
            case 2: //비밀번호 틀림
                model.addAttribute("errMsg", "비밀번호를 확인해 주세요.");
                break;
        }
        return "user/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserEntity param) {
//      로그인 성공 시 아래가 발동
        return "redirect:" + service.login(param);
    }

    @RequestMapping(value = "/join")
    public String join() { return "user/join"; }

    @RequestMapping(value = "/join", method = RequestMethod.POST) // post방식은 method설정
        public String join(UserEntity param) {
        service.join(param);
        System.out.println("uid" + param);
        return "redirect:/user/login";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    @GetMapping("/logout")            // 로그아웃 했을 때 바로 전 페이지로 이동
    public String logout(HttpSession hs, HttpServletRequest req) {
        hs.invalidate();
        String referer = req.getHeader("Referer");
        return "redirect:" + referer;
//        return "redirect:/user/login";
    }

    //@RequestMapping(value="/profile", method=RequestMethod.POST)
    @PostMapping("/profile")
    public String profile(MultipartFile profileImg) {
        return "redirect:" + service.uploadProfile(profileImg);
    }

}
