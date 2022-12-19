package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Participant;
import com.mztalk.mentor.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto2 {

    private Long id;
    private Mentor mentor;
    private String category;
    private String title;
    private String nickname;
    private Long userId;
    private String content;
    private String introduction;
    private String career;
    private int salary;
    private Status status;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .mentor(mentor)
                .category(category)
                .title(title)
                .nickname(nickname)
                .content(content)
                .introduction(introduction)
                .career(career)
                .salary(salary)
                .status(Status.YES)
                .build();
        return board;
    }

    public BoardDto2(Board board){
        this.id = board.getId();
        this.mentor = board.getMentor();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.nickname = board.getNickname();
        this.content = board.getContent();
        this.introduction = board.getIntroduction();
        this.career = board.getCareer();
        this.salary = board.getSalary();
        this.status = board.getStatus();
    }

    @Override
    public String toString() {
        return "BoardDto2{" +
                "id=" + id +
                ", mentor=" + mentor +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", introduction='" + introduction + '\'' +
                ", career='" + career + '\'' +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }
}
