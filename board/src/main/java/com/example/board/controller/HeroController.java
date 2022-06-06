package com.example.board.controller;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.HeroVO;
import com.example.board.service.HeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/hero/*")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @GetMapping("/list")
    public List<HeroVO> getList(){
        return heroService.getList(new Criteria(1,10));
    }

    @GetMapping("/get/{hno}")
    public HeroVO get(@PathVariable("hno") Long heroNumber) {
        return heroService.get(heroNumber);
    }

}
