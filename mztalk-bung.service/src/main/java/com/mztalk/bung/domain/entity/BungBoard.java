package com.mztalk.bung.domain.entity;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungBoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="BungBoard")
@Entity
public class BungBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @Column(nullable = false, length = 20)
    private String boardWriter;
    @Column(nullable = false, length = 100)
    private String boardTitle;
    @Lob
    private String boardContent;
    @Column(nullable = false)
    private Date deadlineDate;
    @Column(nullable = false)
    private Long fullGroup;
    @Column(nullable = false)
    private Long nowGroup;
    @CreationTimestamp
    private Timestamp createDate;
    @CreationTimestamp
    private Timestamp modifyDate;
    @Column(nullable = false)
    private Long boardCount;
    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;
    @Column(nullable = false, length = 10)
    private String category;

    public void mainBoardUpdate(BungBoardDto bungBoardDto) {
        this.boardTitle = bungBoardDto.getBoardTitle();
        this.boardContent = bungBoardDto.getBoardContent();
        this.deadlineDate = bungBoardDto.getDeadlineDate();
        this.fullGroup = bungBoardDto.getFullGroup();
        this.category = bungBoardDto.getCategory();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.modifyDate = timestamp;
    }

    public void changeStatus() {
        this.boardStatus = BoardStatus.NO;
    }

//    public BungBoard(Long boardId, String boardWriter, String boardTitle, String boardContent, Date deadlineDate, Long fullGroup, Long nowGroup, Timestamp createDate, Timestamp modifyDate, Long boardCount, String boardStatus, String category) {
//        this.boardId = boardId;
//        this.boardWriter = boardWriter;
//        this.boardTitle = boardTitle;
//        this.boardContent = boardContent;
//        this.deadlineDate = deadlineDate;
//        this.fullGroup = fullGroup;
//        this.nowGroup = nowGroup;
//        this.createDate = createDate;
//        this.modifyDate = modifyDate;
//        this.boardCount = boardCount;
//        this.boardStatus = boardStatus;
//        this.category = category;
//    }
}

