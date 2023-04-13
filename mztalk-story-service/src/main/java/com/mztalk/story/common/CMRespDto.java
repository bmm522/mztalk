package com.mztalk.story.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T>{

    // CMRespDto, Script 비교
    // 1. 클라이언트에게 응답할 때는 Script가 더 좋음.(응답을 브라우저가 받음)
    // 2. Ajax통신 - CMRespDto(Ajax랑 통신할 떈 이게 더 좋음)
    // 3. Android 통신 - CMRespDto
    //Ajax는 자바쪽으로 서버 연결해서 통신
    //차이는 클라이언트가 응답받을땐 스크립트, 개발자는 코드로 응답받을때가 좋다


    private int code; //1(성공), -1(실패)
    private String message;
    private T data;
}
