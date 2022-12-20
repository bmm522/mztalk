package com.mztalk.main.domain.follow.entity;

import com.mztalk.main.common.BaseTimeEntity;
import com.mztalk.main.domain.friend.Friends;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;


//@Table(
//    uniqueConstraints = {
//        @UniqueConstraint(
//            name="Follow_uk",
//            columnNames = {"fromUserId","toUserId"}
//        )
//    }
//)
@NoArgsConstructor
@Entity
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="Follow_uk",
                        columnNames= {"fromUserId","toUserId"}
                ) //실제로 사용하는 컬럼명
        }
)
//@IdClass(Follow.PK.class)
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략을 DB를 따라간다.
    private int id;



    @JoinColumn(name="fromUserId") //이렇게 컬럼명 만들어.. 니 맘대로 만들지 말고
    @ManyToOne
    private Friends fromUser;  //구독하는 사람 (
    //원하는 이름으로 바꿀때
    @JoinColumn(name="toUserId")
    @ManyToOne
    private Friends toUser;  //구독받는 사람 (페이지주인)































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
