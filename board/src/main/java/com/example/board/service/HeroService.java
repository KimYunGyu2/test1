package com.example.board.service;

import com.example.board.domain.dao.BoardDAO;
import com.example.board.domain.dao.HeroDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HeroService {
    private final HeroDAO heroDAO;

    public List<HeroVO> getList(Criteria criteria) {
        return heroDAO.getList(criteria);
    }

    public HeroVO get(Long heroNumber) {
        return heroDAO.get(heroNumber);
    }

    public void newHero(HeroVO heroVO) {
        heroDAO.newHero(heroVO);
    }

    public boolean modify(HeroVO heroVO) {
        return heroDAO.modify(heroVO);
    }

    public boolean remove(Long heroNumber) {
        return heroDAO.remove(heroNumber);
    }

}
