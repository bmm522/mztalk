package com.mztalk.bung.domain.response;


import com.mztalk.bung.domain.entity.BungAddBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptResponseDto {

    private String addNickname;

    private long addId;

    public AcceptResponseDto(BungAddBoard bungAddBoard){
        this.addNickname = bungAddBoard.getAddNickName();
        this.addId = bungAddBoard.getAddId();
    }
}
