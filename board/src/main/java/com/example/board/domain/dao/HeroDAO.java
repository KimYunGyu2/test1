package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import com.example.board.mapper.HeroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HeroDAO {
    private final HeroMapper heroMapper;
    // 전체 개수 조회
    public List<HeroVO> getList(Criteria criteria) {return heroMapper.getList(criteria);}

    // 1개 조회
    public HeroVO get(Long heroNumber) {return heroMapper.get(heroNumber);}

    // 캐릭터 생성
    public void newHero(HeroVO heroVO) { heroMapper.insert(heroVO);}

    // 캐릭터 수정
    public boolean modify(HeroVO heroVO) {return heroMapper.update(heroVO) == 1;}

    // 캐릭터 삭제
    public boolean remove(Long heroNumber) {return heroMapper.delete(heroNumber) == 1;}

}
