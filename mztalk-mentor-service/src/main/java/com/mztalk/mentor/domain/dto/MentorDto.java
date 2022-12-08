package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Score;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class MentorDto {

    private Long id;
    private ApplicationDto application;
    private BoardDto board;
    private List<Score> scores = new ArrayList<>();
    private List<Mentee> mentees = new ArrayList<>();
    private Status status;

}