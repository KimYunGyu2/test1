package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class BoardControllerTests {
//    마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어 준다.
    private MockMvc mockMvc;

//    서버 환경 및 설정, 요청 등을 처리해주는 WebApplicationContext를 불러온다.
    @Autowired
    private WebApplicationContext webApplicationContext;

//    모든 @Test 실행 전 한 번씩 실행된다.
//    @Test 메소드가 2개라면 두 번 실행된다.
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getListTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "19")
                .param("amount","2")
        )
                .andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void registerTest() throws Exception {
        log.info("추가된 게시글 번호 : " + mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("boardTitle", "테스트 새 글 제목")
                .param("boardContent", "테스트 새 글 내용")
                .param("boardWriter", "Test")
        ).andReturn().getFlashMap());
    }

    @Test
    public void readTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("boardBno", "3"))
                .andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void modifyTest() throws Exception {
        log.info("수정된 게시글 번호 : " + mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("boardBno", "6")
                .param("boardTitle", "바꿀 제목")
                .param("boardContent", "바꿀 내용")
                .param("boardWriter", "ljs0108")
        ).andReturn().getFlashMap());
    }

    @Test
    public void removeTest() throws Exception {
        log.info(mockMvc.perform(
                MockMvcRequestBuilders.get("/board/remove")
                .param("boardBno","12")
        ).andReturn().getModelAndView().getViewName());

    }
}

