package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list); // list jsp에서 list 키 값 사용 가능
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model){
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        System.out.println("iboard" + param.getIboard());
        return "board/detail";
    }

//  Json 형태로 문자열을 만드는 map
//  map은 forEach문 돌릴 수 없음
    @ResponseBody // 필수
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
        public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) {
            System.out.println("param" + param);
            Map<String, Integer> data = new HashMap();
            data.put("result", 1);

            return data;
        }
    }

