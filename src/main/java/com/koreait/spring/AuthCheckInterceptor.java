package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


public class AuthCheckInterceptor implements HandlerInterceptor {
    private final String[] needLoginUriArr = {"/board/writeMod", "/board/favList", "/user/profile"};

//   아래에서 필요한 걸 선택하여 사용하면 됨

    // controller로 보내기 전에 처리하는 곳
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }
    // jsp파일을 열기 직전 (controller의 handler가 끝나면 처리하는 곳) **컨트롤러는 갔다온 것
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri : " + uri);

        if(Arrays.asList(needLoginUriArr).contains(uri)) {
            UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");
            if(loginUser == null) {
                System.out.println("viewName : " + modelAndView.getViewName());
                modelAndView.setViewName("/user/needLogin");
            }
        }
    }
    // 화면(view)까지 모두 거친 다음 처리
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
