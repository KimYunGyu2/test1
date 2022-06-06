package com.example.board.mapper;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;

    Long[] arr1 = {524304L, 524305L, 524306L, 524307L, 524308L};


    @Test
    public void replyMapperTest(){
        log.info(replyMapper + "");
    }

    @Test
    public void insertTest(){
        // 최근 5개의 게시글에 2개씩 댓글 달기
        /*ReplyVO replyVO = new ReplyVO();
        replyVO.setBoardBno(524308L);
        replyVO.setReplyContent("반갑습니다");
        replyVO.setReplyWriter("임진석");

        replyMapper.insert(replyVO);
        log.info("================================");
        log.info("게시글번호 " + replyVO.getBoardBno());
        log.info("댓글 번호 " + replyVO.getReplyNumber());
        log.info("댓글 내용 " + replyVO.getReplyContent());
        log.info("댓글 작성자 " + replyVO.getReplyWriter());
        log.info("================================");*/
        IntStream.rangeClosed(1,10).forEach(i -> {
            ReplyVO replyVO = new ReplyVO();
            replyVO.setBoardBno(arr1[i%5]);
            replyVO.setReplyContent("반갑습니다" + i);
            replyVO.setReplyWriter("임진석" + i);
            replyMapper.insert(replyVO);
        }); // rangeClosed(start,end) end값 포함 range(start,end) end값 포함 안함

    }

    @Test
    public void getReplyTest() {
        Long replyNumber = 5L;
        log.info("=========================================");
        log.info(replyMapper.getReply(replyNumber).toString());
        log.info("=========================================");
    }

    @Test
    public void deleteReplyTest() {
        log.info("delete" + replyMapper.delete(6L));
    }

    @Test
    public void getListTest() {
        replyMapper.getList(new Criteria(2, 10), 524306L)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }
}
