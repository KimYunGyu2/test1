package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HeroMapperTests {
    @Autowired
    private HeroMapper heroMapper;

    @Test
    public void getListTest() {
        log.info("=========================");
        log.info("=========getList=========");
        log.info("=========================");
        heroMapper.getList(new Criteria(2,10)).stream().map(HeroVO::toString).forEach(log::info);;
    }

    @Test
    public void getTest() {
        log.info("=========================");
        log.info("===========get===========");
        log.info("=========================");
        heroMapper.get(5L);
    }

    @Test
    public void insertTest() {
        log.info("=========================");
        log.info("==========insert=========");
        log.info("=========================");
        HeroVO heroVO = new HeroVO();
        heroVO.setHeroClass("전사");
        heroVO.setHeroLevel("33");
        heroVO.setHeroName("zㅣ존진석");
        heroMapper.insert(heroVO);
    }

    @Test
    public void updateTest() {
        log.info("=========================");
        log.info("==========update=========");
        log.info("=========================");
        HeroVO heroVO = heroMapper.get(5L);
        heroVO.setHeroName("S2푸른하늘S2");
        heroVO.setHeroClass("드루이드");
        heroMapper.update(heroVO);
    }

    @Test
    public void deleteTest(){
        log.info("=========================");
        log.info("==========delete=========");
        log.info("=========================");
        heroMapper.delete(1L);
    }
}

// ㅎㅇㅎㅇ 나 지수