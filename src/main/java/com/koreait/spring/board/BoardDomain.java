package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// select할 때 join을 걸면서 달라지는 컬럼명을 하나로 통일할 것
public class BoardDomain extends BoardEntity {
    private String writerNm;
    private String profileImg;
}
