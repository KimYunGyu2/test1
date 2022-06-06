package com.example.board.service;

import com.example.board.domain.dao.ReplyDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    Long[] arr1 = {524304L, 524305L, 524306L, 524307L, 524308L};


    @Test
    public void replyServiceTest(){
        log.info(replyService + "");
    }

    @Test
    public void registerTest(){
        // 최근 5개의 게시글에 2개씩 댓글 달기
        /*ReplyVO replyVO = new ReplyVO();
        replyVO.setBoardBno(524308L);
        replyVO.setReplyContent("반갑습니다");
        replyVO.setReplyWriter("임진석");

        replyService.insert(replyVO);
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
            replyService.register(replyVO);
        }); // rangeClosed(start,end) end값 포함 range(start,end) end값 포함 안함

    }

    @Test
    public void getReplyTest() {
        Long replyNumber = 5L;
        log.info("=========================================");
        log.info(replyService.read(replyNumber).toString());
        log.info("=========================================");
    }

    @Test
    public void deleteReplyTest() {
        log.info("delete" + replyService.remove(32L));
    }

    @Test
    public void modifyTest() {
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyNumber(31L);
        replyVO.setReplyContent("수정완료");
        replyService.modify(replyVO);
    }

    @Test
    public void getListTest() {
        replyService.getList(new Criteria(2, 10), 524306L)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }
}
