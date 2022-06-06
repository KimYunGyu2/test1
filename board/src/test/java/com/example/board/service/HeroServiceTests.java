package com.example.board.service;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HeroServiceTests {
    @Autowired
    private HeroService heroService;

    @Test
    public void getListTest(){
        heroService.getList(new Criteria(2,10));
    }

    @Test
    public void get(){
        heroService.get(5L);
    }

    @Test
    public void newHero(){
        HeroVO heroVO = new HeroVO();
        heroVO.setHeroLevel("44");
        heroVO.setHeroName("으아아악");
        heroVO.setHeroClass("수도사");
        heroService.newHero(heroVO);
    }

    @Test
    public void modify(){
        HeroVO heroVO = new HeroVO();
        heroVO.setHeroNumber(6L);
        heroVO.setHeroName("역삼역3번출구앞이야");
        heroVO.setHeroClass("도적");
        heroService.modify(heroVO);
    }

    @Test
    public void remove(){
        heroService.remove(44L);
    }
}
