package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity param);
//  Controller에서 미리 BoardCmtEntity값을 받겠다고 설정함
    List<BoardCmtDomain> selBoardCmt (BoardCmtEntity param);
}
