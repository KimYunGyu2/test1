package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    // 댓글 추가
    public void insert(ReplyVO replyVO);
    // 댓글 1개 조회
    public ReplyVO getReply(Long replyNumber);
    // 삭제
    public int delete(Long replyNumber);
    // 수정
    public int update(ReplyVO replyVO);
    // 댓글 목록
    public List<ReplyVO> getList(@Param("criteria") Criteria criteria, @Param("boardBno") Long boardBno);
    // 최근 5개 @Param은 Mapper 인터페이스에서 사용 (매개변수 설명)
}
