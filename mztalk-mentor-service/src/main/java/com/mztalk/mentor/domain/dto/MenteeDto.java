package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Participant;
import com.mztalk.mentor.domain.entity.Payment;
import com.mztalk.mentor.domain.entity.Score;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenteeDto {

    private Long id;
    private String nickname;
    private List<Participant> participants = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<Score> scores;
    private List<Mentor> mentors = new ArrayList<>();

}
