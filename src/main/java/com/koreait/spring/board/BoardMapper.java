package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity param);
    List<BoardDomain> selBoardList();
    BoardDomain selBoard(BoardDTO param);
    int updBoard(BoardEntity param);
    int delBoard(BoardEntity param);
}
