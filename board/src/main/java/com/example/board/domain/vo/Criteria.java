package com.example.board.domain.vo;

// Criteria : 검색의 기준

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
@AllArgsConstructor
public class Criteria {     // 화면쪽으로 넘겨주기 위해서 db의 컬럼을 쓴게 아니라 DTO에 가깝다
    private int pageNum;    // 페이지 번호
    private int amount;     // 페이지의 게시글 개수
    private String type;    // 검색시 검색 조건
    private String keyword; // 검색어

    public Criteria() {
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String getListLink(){    // 이전 페이지에서 어떤 페이지에 머물러있는지 정보를 기억하기 위해 사용
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("") // 여러 파라미터를 하나의 url로 제공
                .queryParam("pageNum",this.pageNum)
                .queryParam("amount",this.amount)
                .queryParam("type",this.type)
                .queryParam("keyword",this.keyword);

        return builder.toUriString(); // uri 문자열로 반환
    }

    public String[] getTypes() {
        return type == null ? new String[] {} : type.split("");
    }

}
