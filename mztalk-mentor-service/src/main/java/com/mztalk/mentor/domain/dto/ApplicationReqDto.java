package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Application;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationReqDto {
    @ApiModelProperty(value="신청자의 식별자", example = "1", required = true)
    private Long userId;
    @ApiModelProperty(value="신청자의 이름", example = "홍길동", required = true)
    private String name;
    @ApiModelProperty(value="신청자의 연락처", example = "01011112222", required = true)
    private String phone;
    @ApiModelProperty(value="신청자의 이메일", example = "mztalk@google.com", required = true)
    private String email;
    @ApiModelProperty(value="신청자의 직업", example = "대학생", required = true)
    private String job;
    @ApiModelProperty(value="신청자의 은행고유번호", example = "004", required = true)
    private String bank;
    @ApiModelProperty(value="신청자의 생년월일", example = "880101", required = true)
    private String birthday;
    @ApiModelProperty(value="신청자의 계좌번호", example = "11011112222", required = true)
    private String account;

    public Application toEntity(){
        Application application = Application.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .job(job)
                .bank(bank)
                .account(account)
                .birthday(birthday)
                .authStatus(AuthStatus.NO)
                .status(Status.YES)
                .build();
        return application;
    }
}
