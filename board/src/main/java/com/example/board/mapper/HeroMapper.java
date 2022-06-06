package com.example.board.mapper;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeroMapper {
    // 캐릭터 전체 조회
    public List<HeroVO> getList(Criteria criteria);

    // 캐릭터 1개 조회
    public HeroVO get(Long heroNumber);

    // 캐릭터 생성
    public void insert(HeroVO heroVO);

    // 캐릭터 수정
    public int update(HeroVO heroVO);

    // 캐릭터 삭제
    public int delete(Long heroNumber);
}
