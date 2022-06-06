package com.example.board.controller;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/*
*   Task        URL                 Method      Parameter       Form        URL 이동
*
*   전체목록     /board/list          GET
*   등록처리     /board/register      POST        모든 항목        필요         /board/list
*   조회        /board/read          GET          bno
*   삭제처리     /board/remove        GET         bno                         /board/list
*   수정처리     /board/modify        POST        모든 항목        필요         /board/list
*
* */

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    // 리턴 타입을 void로 작성해도 되지만
    // 다른 컨트롤러에서 getList()를 호출하기 때문에
    // html 경로를 직접 문자열로 작성해야 한다.
    public String getList(Criteria criteria, Model model){
        log.info("*************");
        log.info("/list");
        log.info("*************");
        model.addAttribute("boardList", boardService.getList(criteria));
        // model 객체에 boardService의 getList메서드를 이용한 값을 담는다. (list를 불러오기 위해)
        model.addAttribute("pageDTO",new PageDTO(criteria, boardService.getTotal()));
        // model 객체에 PageDTO를 담는다. (페이징 처리를 위해)
        return "/board/list";   // /board/list 값을 반환하여 페이지 이동
    }
    @GetMapping("/register")
    public void register() {}   // /register url이 들어오면 등록 페이지로 이동

    @PostMapping("/register")
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr){
        // Redirect 방식으로 반환하고 화면에 파라미터를 전달해주기 위해 rttr 매개변수로 받음
        log.info("*************");
        log.info("/register");
        log.info("*************");
        boardService.register(boardVO); // boardService에서 register 메서드 불러옴

//        redirect 방식으로 전송할 때에는 request scope를 사용할 수 없다.
//        RedirectAttributes 객체는 두 가지 방법을 제공해준다.
//        1. 쿼리 스트링
//          URL 뒤에 전달한 KEY와 VALUE를 쿼리스트링으로 연결해준다.
//        rttr.addAttribute("boardBno", boardVO.getBoardBno());

//        2. Flash 사용
//          세션에 파라미터를 저장하고, request객체가 초기화 된 후 다시 저장해준다.
        rttr.addFlashAttribute("boardBno", boardVO.getBoardBno());
        // 화면에 boardVO 로부터 받아온 게시글 번호를 넘겨줌
        return new RedirectView("/board/list"); // Redirect 방식으로 url 이동
    }

    @GetMapping({"/read", "/modify"})   // url이 read나 modify가 들어오면
    public void read(Long boardBno, Criteria criteria, HttpServletRequest request, Model model){
        log.info("*************");
        String requestURL = request.getRequestURI(); // 요청한 url 값 받음
        log.info(requestURL.substring(requestURL.lastIndexOf("/")));
        // url 값을 오른쪽에서 왼쪽으로 첫 "/"에서 부터 자름
        log.info("/read");
        log.info("*************");
        model.addAttribute("board", boardService.read(boardBno));
        // 상세보기에 해당 게시글 번호의 boardVO를 넘겨줌(화면에서 이용가능)
    }

//    수정
//    Redirect 방식으로 전송
//    Flash로 데이터 전달 - 수정 성공 시에만 "success" 전달
    @PostMapping("/modify")
    public RedirectView modify(BoardVO boardVO, Criteria criteria, RedirectAttributes rttr ){
        log.info("*************");
        log.info("***modify***");
        log.info("*************");
        if(boardService.modify(boardVO)) {
                // 컨트롤러에서 다른 컨트롤러의 매개변수로 파라미터를 전달할 때에는
                // addAttribute(), 쿼리스트링 방식으로 전달해야 받을 수 있다.
                // Flash방식은 최종 응답 화면에서 파라미터를 전달할 때에만 사용하도록 한다.
            rttr.addAttribute("boardBno", boardVO.getBoardBno());
            rttr.addAttribute("pageNum",criteria.getPageNum());
            rttr.addAttribute("amount",criteria.getAmount());
            // 컨트롤러에서 위의 값들을 이용할 수 있게 addAttribute 방식으로 넘겨준다.
        }
        return new RedirectView("/board/read");

    }

    @PostMapping("/remove")
    public String remove(Long boardBno, Criteria criteria, Model model){
        log.info("*************");
        log.info("/remove");
        log.info("*************");
        boardService.remove(boardBno);
        // 다른 컨트롤러로 이동하고자 할 때 해당 메소드를 직접 실행한다.
        // 만약 필요한 파라미터가 있다면 최초 요청 처리 메소드를 통해 파라미터를 전달해준다.
        // remove에는 model, criteria가 필요없지만 getList에서 사용하기 위해 넘겨준다.
        return getList(criteria, model);
    }


}
