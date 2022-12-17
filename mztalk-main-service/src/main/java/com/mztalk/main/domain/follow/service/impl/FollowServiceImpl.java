package com.mztalk.main.domain.follow.service.impl;


import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.repository.BoardRepository;
import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.domain.follow.repository.FollowRepository;
import com.mztalk.main.domain.follow.service.FollowService;
import com.mztalk.main.handler.exception.CustomApiException;
import com.mztalk.main.handler.exception.ExceptionCode;
import com.mztalk.main.handler.exception.FollowException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
   // private final BoardRepository boardRepository;

//    private final EntityManager em;
//
//    //리스트 뽑아주기...
//    @Transactional(readOnly = true)
//    public List<FollowDto> followDtoList(Long own, Long userno ){
//
//        StringBuffer sb = new StringBuffer();
//
//
//
//
//        return null;
//    }

    @Override
    @Transactional
    public void follow(Long toUserNo, Long fromUserNo) {
        try {
            followRepository.mFollow(fromUserNo, toUserNo);
        } catch(Exception e){
            throw new FollowException(ExceptionCode.ALREADY_EXIST_FOLLOW);
        }

    }

    @Override
    @Transactional
    public void unFollow(Long toUserId, Long fromUserId) {

        followRepository.mUnFollow(fromUserId, toUserId);
    }

//    @Override
//    public Boolean addFollow(Long toUserId, Long fromUserId) {
//
//        if(Objects.equals(toUserId, fromUserId)){
//            throw new FollowException(ExceptionCode.SAME_ACCOUNT);
//        }
//
//        Board toUser = boardRepository.findByUserId(toUserId)
//                .orElseThrow(()-> new CustomApiException(ExceptionCode.NOT_FOUND_ACCOUNT));
//
//        Board fromUser = boardRepository.findByUserId(fromUserId)
//                .orElseThrow(() -> new CustomApiException(ExceptionCode.NOT_FOUND_ACCOUNT));
//
//        Optional<Follow> relation = getFollowRelation(toUser.getId(), fromUser.getId());
//        if(relation.isPresent()) {
//            throw new FollowException(ExceptionCode.ALREADY_EXIST_FOLLOW);
//        }
//        followRepository.save(new Follow(toUserId,  fromUserId));
//
//        return true;
//    }
//
//    private Optional<Follow> getFollowRelation(Long toUserId, Long fromUserId) {
//
//        return followRepository.findByToUserIdAndFromUserId(toUserId, fromUserId);
//    }
}
