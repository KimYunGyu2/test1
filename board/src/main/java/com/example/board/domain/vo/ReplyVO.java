package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVO {
    private Long replyNumber;
    private Long boardBno;
    private String replyWriter;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
}
