package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtEntity {
    private int iboard;
    private int icmt;
    private int iuser;
    private String cmt;
    private String regdate;
}
