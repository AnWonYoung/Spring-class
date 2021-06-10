package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardFavController {

    @Autowired
    private BoardFavService service;

    @PostMapping("/fav")
    public Map<String, Integer> insFav(@RequestBody BoardFavEntity param) {
            Map<String, Integer> result = new HashMap<>();
            return result;
    }

    @GetMapping("/fav")
    public Map<String, Integer> selFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
        return result;
    }

    @DeleteMapping ("/fav")
    public Map<String, Integer> delFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
        return result;
    }
}
