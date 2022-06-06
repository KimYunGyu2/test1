package com.example.board.controller;
/*
    REST(Representational State Transfer) URL 이 하나의 정보
    하나의 URI는 하나의 고유한 리소스(데이터)를 대표하도록 설계한다.
    예 ) /board/123 : 게시글 중 123번 게시글의 전체 정보

 */

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.domain.vo.TestVO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 등록
    // 브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
    // consumes : 전달받은 데이터 타입
    // produces : 콜백함수로 결과를 전달할 때의 타입
    // @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입할 때 사용
    // ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {
        log.info("replyVO : " + replyVO);
        replyService.register(replyVO);
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    // 게시글 댓글 1개 조회
    @GetMapping("/{rno}")
    public ReplyVO read(@PathVariable("rno") Long replyNumber) {
        log.info("read....." + replyNumber);
        return replyService.read(replyNumber);
    }

    // 댓글 전체 목록 조회
    @GetMapping("/list/{bno}/{page}")
    public List<ReplyVO> getList(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno) {
        log.info("list....." + boardBno);
        return replyService.getList(new Criteria(pageNum, 10), boardBno);
    }

    // 댓글 삭제
    @DeleteMapping("/{rno}")
    public String remove(@PathVariable("rno") Long replyNumber){
        log.info("remove...." + replyNumber);
        replyService.remove(replyNumber);
        return "삭제 성공";
    }

    // 댓글 수정
    // PUT : 자원의 전체 수정, 자원 내 모든 필드를 전달해야 함. 일부만 전달할 경우 오류
    // PATCH : 자원의 일부 수정, 수정할 필드만 전송(자동 주입이 아닌 부분만 수정하는 쿼리문에서 사용)
    @PatchMapping(value = "/{rno}", consumes = "application/json")
    public String modify(@PathVariable("rno") Long replyNumber, @RequestBody ReplyVO replyVO) {
        log.info("modify......." + replyVO);
        log.info("modify......." + replyNumber);

        if(replyVO.getReplyWriter() == null) {
            replyVO.setReplyWriter(Optional.ofNullable(replyVO.getReplyWriter()).orElse("anonoymous"));
        }
        replyVO.setReplyNumber(replyNumber);
        replyService.modify(replyVO);

        return "댓글 수정 성공";
    }


    @PostMapping("modify/{bno}/{rno}")
    public List<ReplyVO> modify(@PathVariable("rno") Long replyNumber, @PathVariable("bno") Long boardBno) {
        log.info("modify...." + replyNumber);
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyNumber(replyNumber);
        replyVO.setReplyContent("수정완료");
        replyService.modify(replyVO);
        return replyService.getList(new Criteria(), boardBno);
    }

    // 1번 매개변수 없고 리턴은 문자열
    // 2번 매개변수 1개 있고 리턴은 문자열
    // 3번 매개변수 없고 리턴은 JSON Object
    // 4번 매개변수 여러 개 있고 리턴은 JSON Object
    // 5번 매개변수 여러 개 있고 리턴은 JSON Array
    @GetMapping("/test01")
    public String test01(){
        return "test01성공";
    }

    @GetMapping("/test02/{name}")
    public String test02(@PathVariable("name") String name){
        return name + "성공";
    }

    @GetMapping("/test03")
    public TestVO test03(){
        TestVO testVO = new TestVO();
        testVO.setAge(29);
        testVO.setName("임진석");
        return testVO;
    }

    @PostMapping("/test04")
    public TestVO test04(@RequestBody TestVO testVO){
        log.info("들어옴");
        return testVO;
    }

    /*@PostMapping("/test05")
    public List<TestVO> test05(@RequestBody TestVO testVO){
        log.info("들어옴");

        return List<testVO>;
    }*/

}
