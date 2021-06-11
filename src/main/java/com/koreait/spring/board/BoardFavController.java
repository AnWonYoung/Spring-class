package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // jsp를 뿌리는 용도가 아닐 때는 사용해도 되지만 같이 사용하면 일반 controller
                // RestController에서는 주소값 통일이 FM
@RequestMapping("/board")
public class BoardFavController {

    @Autowired
    private BoardFavService service;

    @Autowired
    private BoardService service2; // favList 처리를 위해 추가

    @PostMapping("/fav")
    public Map<String, Integer> insFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
            result.put("result", service.insFav(param));
            return result;
    }
//  좋아요 리스트
    @RequestMapping("/fav")
    public List<BoardDomain> selFavBoardList(BoardDTO param) {
        param.setSelType(1);
        return service2.selBoardList(param);
    }

//  객체만 넣어서 보내주는 처리
//   getFavAjax에서는 쿼리스트링으로 보내지 않고 주소 키값만 전달하도록 처리
    @GetMapping("/fav/{iboard}")
    public Map<String, Integer> selFav(BoardFavEntity param, @PathVariable int iboard) {
        param.setIboard(iboard);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.selFav(param));
        return result;
    }

    @DeleteMapping ("/fav")
    public Map<String, Integer> delFav(BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delFav(param));
        return result;
    }
}
