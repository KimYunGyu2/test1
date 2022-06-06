package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HeroDaoTests {
    @Autowired
    private HeroDAO heroDAO;

    @Test
    public void getListTest() {
        log.info("=========================");
        log.info("=========getList=========");
        log.info("=========================");
        heroDAO.getList(new Criteria(2, 10));
    }

    @Test
    public void getTest() {
        log.info("=========================");
        log.info("===========get===========");
        log.info("=========================");
        heroDAO.get(5L);
    }

    @Test
    public void insertTest(){
        log.info("=========================");
        log.info("===========new===========");
        log.info("=========================");
        HeroVO heroVO = new HeroVO();
        heroVO.setHeroClass("초보자");
        heroVO.setHeroName("지존짱짱맨");
        heroVO.setHeroLevel("91");
        heroDAO.newHero(heroVO);
    }

    @Test
    public void modifyTest(){
        log.info("=========================");
        log.info("==========modify=========");
        log.info("=========================");
        HeroVO heroVO = heroDAO.get(2L);
        heroVO.setHeroName("임진석99");
        heroVO.setHeroClass("사냥꾼");
        heroDAO.modify(heroVO);
    }

    @Test
    public void deleteTest() {
        log.info("=========================");
        log.info("===========del===========");
        log.info("=========================");
        heroDAO.remove(3L);
    }
}
