package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.File;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private Long id;
    private Mentor mentor;
    private File file;
    private String name;
    private String phone;
    private String email;
    private String job;
    private String bank;
    private String account;
    private AuthStatus authStatus;
    private Status status;

}
