package com.mztalk.story.domain.follow;
import com.mztalk.story.status.FollowStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name="Follow",
        uniqueConstraints={
                @UniqueConstraint(
                        name = "follow_uk",
                        columnNames={"fromUserId","toUserId"}
                )
        }
)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략을 DB를 따라간다.
    private long id;

    @JoinColumn(name = "fromUserId")
    private Long fromUserId;  //팔로우하는 사람

    @JoinColumn(name = "toUserId")
    private Long toUserId;  //팔로우 받는 사람 (페이지주인)

    @CreationTimestamp // 자동으로 현재시간 담김
    private Timestamp createDate;

    private String postImageUrl;

    @Enumerated(EnumType.STRING)
    private FollowStatus followStatus; // 팔로우상태 status
}
