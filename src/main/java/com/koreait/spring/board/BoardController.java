package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(BoardDTO param, Model model) {
        List<BoardDomain> list = service.selBoardList(param);
        model.addAttribute("list", list); // list jsp에서 list 키 값 사용 가능
        model.addAttribute("maxPageVal", service.selMaxPageVal(param));
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model){
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        System.out.println("iboard" + param.getIboard());
        return "board/detail";
    }
//  글 수정하기
    @GetMapping("/writeMod")
    public void writeMod(BoardDTO param, Model model) {
        System.out.println("param = " + param);
        if(param.getIboard() > 0) {
            model.addAttribute("data", service.selBoard(param));
        }
    }

    @PostMapping("/writeMod")
    public String writeMod(BoardEntity param) {
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard=" + iboard;
    }

    @GetMapping("/delBoard")
    public String delBoard(BoardEntity param) {
        service.delBoard(param);
        return "redirect:list";
    }

//  Json 형태로 문자열을 만드는 map
//  map은 forEach문 돌릴 수 없음
//  Json 형태로 js에서 담아서 날리면 RequestBody를 기입
    @ResponseBody // 필수
    @RequestMapping(value = "/cmt" , method = RequestMethod.POST)
        public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param) {

            int result = service.insBoardCmt(param);
            Map<String, Integer> data = new HashMap();

            data.put("result", result);
            return data;
        }
//      쿼리스트링으로 넘어와서 @RequestBody는 적지 않음
        @ResponseBody
        @RequestMapping(value = "/cmt/{iboard}")
        public List<BoardCmtDomain> cmtSel(@PathVariable int iboard) {
            BoardCmtEntity param = new BoardCmtEntity();
            param.setIboard(iboard);
//          iboard값 넘어오는지 체크
            System.out.println("param : " + param);
            return service.selBoardCmt(param);
        }

        @ResponseBody
        @RequestMapping(value = "/cmt", method = RequestMethod.PUT)
        public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param) {
            int result = service.updBoardCmt(param);
            Map<String, Integer> data = new HashMap<>();
            data.put("result", result);
            return data;
        }

        @ResponseBody
        @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
        public Map<String, Integer> cmtDel(@PathVariable("icmt") int icmt) {
            BoardCmtEntity param = new BoardCmtEntity();
            param.setIcmt(icmt);
            int result = service.delBoardCmt(param);
            Map<String, Integer> data = new HashMap<>();
            data.put("result", result);
            return data;
        }
//      위에서는 model을 사용해서 jsp를 뿌렸지만 이번에는 ajax로만 사용
        @GetMapping("/favList")
        public void favList() {}

    }

