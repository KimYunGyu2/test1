package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class HeroVO {
    private Long heroNumber;
    private String heroLevel;
    private String heroName;
    private String heroClass;
    private String heroRegisterDate;
    private String heroUpdateDate;
}
