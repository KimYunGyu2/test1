package com.example.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PageDTO {
    private Criteria criteria;
    private int startPage;          // 몇 페이지에서 시작인지 37일 경우 31~40인걸 알려주기 위해 사용
    private int endPage;            // 몇 페이지에서 끝인지
    private int realEnd;
    private int pageCount;          // 실제 마지막 페이지가 들어가게 적용
    private boolean prev, next;     // 현재 페이지에서 왼쪽으로 이동 가능한지 오른쪽으로 이동 가능한지

    private int total;

    public PageDTO(Criteria criteria, int total) {
        this(criteria, 10, total);
    }

    public PageDTO(Criteria criteria, int pageCount, int total) {
        this.criteria = criteria;
        this.total = total;
        this.endPage = (int)Math.ceil(criteria.getPageNum()/(double)pageCount) * pageCount;
        this.startPage = this.endPage - pageCount + 1;
        this.realEnd = (int)Math.ceil((double)total / criteria.getAmount()); // ex) 192 / 10 = 20
        if(realEnd < this.endPage) {    // ex) total = 62, amount = 10, realEnd = 7 따라서 endPage = 7
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1; // startPage 가 1보다 크면 prev 는 true
        this.next = this.endPage < realEnd; // endPage 가 realEnd 보다 작으면 next 는 true
    }
}
