package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//파라미터 값 보내주는 용도
public class BoardDTO {
    private int iboard;
    private int iuser;
    private int selType; // 0 : board list, 1: fav list 분리해서 처리
    private int page = 1; // 페이징 처리, 디폴트 값 주기
    private int startIdx;
    private int recordCnt = 5;
    private int searchType;
    private String searchText;
}
