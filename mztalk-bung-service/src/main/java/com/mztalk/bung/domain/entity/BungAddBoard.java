package com.mztalk.bung.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="BungAddBoard")
@Entity
public class BungAddBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addId;
    @Lob
    private String addContent;
    @Column(length = 100)
    private String addPhone;
    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;
    @Column(nullable = false, length = 20)
    private String addNickName;
    @ManyToOne
    @JoinColumn(name="boardId")
    @JsonManagedReference // 순환참조 방지(자식->부모)
    private BungBoard bungBoard;

    public static BungAddBoard createBungAddBoard(ConcurrentHashMap<String, String> bungAddBoardMap, BungBoard bungBoard) {
        BungAddBoard bungAddBoard = new BungAddBoard();
        bungAddBoard.addContent = bungAddBoardMap.get("addContent");
        bungAddBoard.addPhone = bungAddBoardMap.get("addPhone");
        bungAddBoard.boardStatus = BoardStatus.NO;
        bungAddBoard.addNickName = bungAddBoardMap.get("addNickName");
        bungAddBoard.addAddBungBoard(bungBoard);
        return bungAddBoard;
    }

    // 삭제 예정
//    public static BungAddBoard createWriterBungAddBoard(ConcurrentHashMap<String, String> bungAddBoardMap, BungBoard bungBoard) {
//        BungAddBoard bungAddBoard = new BungAddBoard();
////        bungAddBoard.addPhone = bungAddBoardMap.get("addPhone");
//        bungAddBoard.boardStatus = BoardStatus.YES;
//        bungAddBoard.addNickName = bungAddBoardMap.get("writer");
//        System.out.println("이거 뭐나옴?" + bungBoard.getBoardId());
//        bungAddBoard.addAddBungBoard(bungBoard);
//        return bungAddBoard;
//    }

    public void changeStatus() {
        this.boardStatus = BoardStatus.YES;
    }

    public void updateAddBoard(BungAddBoardDto bungAddBoardDto) {
        this.addContent = bungAddBoardDto.getAddContent();
        this.addNickName = bungAddBoardDto.getAddNickName();
        this.addPhone = bungAddBoardDto.getAddPhone();
    }

    public void addAddBungBoard(BungBoard bungBoard){
        if(this.bungBoard != null) {
            this.bungBoard.getBungAddBoards().remove(this);
        }
        this.bungBoard = bungBoard;
        bungBoard.getBungAddBoards().add(this);
    }
}
