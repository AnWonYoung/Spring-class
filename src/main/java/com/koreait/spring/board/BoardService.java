package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import com.koreait.spring.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;
    @Autowired
    private BoardCmtMapper cmtMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private MyUtils myUtils;

    public List<BoardDomain> selBoardList() {
     return mapper.selBoardList();
    }

    public BoardDomain selBoard(BoardDTO param) {
        return mapper.selBoard(param);
    }

    public int writeMod(BoardEntity param) {
        param.setIuser(myUtils.getLoginUserPk());

        if(param.getIboard() == 0) { //등록
            mapper.insBoard(param);
        } else { //수정
            mapper.updBoard(param);
        }
        return param.getIboard();
    }

    public int delBoard(BoardEntity param) {
        //댓글 먼저 삭제한다.
        BoardCmtEntity cmtParam = new BoardCmtEntity();
        cmtParam.setIboard(param.getIboard());
        cmtMapper.delBoardCmt(cmtParam);

        param.setIuser(myUtils.getLoginUserPk());
        return mapper.delBoard(param);
    }

    public int insBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmt(BoardCmtEntity param) {
        return cmtMapper.selBoardCmt(param);
    }

    public int delBoardCmt (BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.delBoardCmt(param);
    }

    public int updBoardCmt (BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.updBoardCmt(param);
    }
}
