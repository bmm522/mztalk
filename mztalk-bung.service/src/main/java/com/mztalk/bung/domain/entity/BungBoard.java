package com.mztalk.bung.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungBoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import reactor.core.publisher.Sinks;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


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
    private Long boardWriterId;
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
    @Column(nullable = false, length = 50)
    private String address;
    @OneToMany(mappedBy = "bungBoard")
    @JsonBackReference // 순환참조 방지(부모->자식)
    private List<BungAddBoard> bungAddBoards;

    public void mainBoardUpdate(BungBoardDto bungBoardDto) {
        this.boardTitle = bungBoardDto.getBoardTitle();
        this.boardContent = bungBoardDto.getBoardContent();
        this.deadlineDate = Date.valueOf(bungBoardDto.getDeadlineDate());
        this.fullGroup = bungBoardDto.getFullGroup();
        this.category = bungBoardDto.getCategory();
        this.address = bungBoardDto.getAddress();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.modifyDate = timestamp;
    }

    public void changeStatus() {
        this.boardStatus = BoardStatus.NO;
    }

    public void addAddBungBoard(BungAddBoard bungAddBoard){
        this.bungAddBoards.add(bungAddBoard);

        if(bungAddBoard.getBungBoard() != this) {
            bungAddBoard.addAddBungBoard(this);
        }
    }

//    @Override
//    public String toString() {
//        return "BungBoard{" +
//                "boardId=" + boardId +
//                ", boardWriter='" + boardWriter + '\'' +
//                ", boardTitle='" + boardTitle + '\'' +
//                '}';
//    }

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
