package com.mztalk.main.domain.follow.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략을 DB를 따라간다.
    private int id;

    private Long fromUserId;  //구독하는 사람 (
    //원하는 이름으로 바꿀때

    private Long toUserId;  //구독받는 사람 (페이지주인)

    @CreationTimestamp // 자동으로 현재시간 담김
    private Timestamp createDate;


    private String postImageUrl;



























//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Id
//    @Column(name = "fromUser", insertable = false, updatable = false)
//    private Long fromUser;  //내가 친구 추가한 사람
//                                //내가 등록한 사람
//                                //영어를 못해서...(from)
//
////    @JsonIgnoreProperties({"board"})
////    @JoinColumn(name="fromUserId")
////    @ManyToOne
////    private Board fromUser;
////
////
////    @JsonIgnoreProperties({"board"})
////    @JoinColumn(name="toUserId")
////    @ManyToOne
////    private Board toUser;
//
//
//    @Id
//    @Column(name = "toUser", insertable = false, updatable = false)
//    private Long toUser;    //나를 친구로 추가한 사람
//                                //나를 등록한 사람
//                                //to
//
//    public Follow(Long fromUser, Long toUser) {
//        this.fromUser = fromUser;
//        this.toUser = toUser;
//    }
//
//    public static class PK implements Serializable {
//        Long fromUser;
//        Long toUser;
//    }

//    @Builder
//    public Follow(Long id, Board fromUser, Board toUser){
//        this.id = id;
//        this.fromUser= fromUser;
//        this.toUser = toUser;
//    }



}
