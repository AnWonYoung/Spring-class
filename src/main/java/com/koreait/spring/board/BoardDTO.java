package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//파라미터 값 보내주는 용도
public class BoardDTO {
    private int iboard;
    private int startIdx;
    private int recordCnt;
    private int searchType;
    private String searchText;
}
