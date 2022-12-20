package com.mztalk.main.domain.follow.service.impl;


import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.repository.BoardRepository;
import com.mztalk.main.domain.follow.dto.FollowDto;
import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.domain.follow.repository.FollowRepository;
import com.mztalk.main.domain.follow.repository.FollowTestRepository;
import com.mztalk.main.domain.follow.service.FollowService;
import com.mztalk.main.handler.exception.CustomApiException;
import com.mztalk.main.handler.exception.ExceptionCode;
import com.mztalk.main.handler.exception.FollowException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    private final FollowTestRepository followTestRepository;

    private final EntityManager em;


    @Override
    @Transactional
    public void follow(Long toUserNo, Long fromUserNo) {
        try {
            followTestRepository.mFollow(fromUserNo, toUserNo);
//            followRepository.mFollow(fromUserNo, toUserNo);
        } catch(Exception e){
            throw new FollowException(ExceptionCode.ALREADY_EXIST_FOLLOW);
        }

    }

    @Override
    @Transactional
    public void unFollow(Long toUserId, Long fromUserId) {

        followRepository.mUnFollow(fromUserId, toUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FollowDto> followList(Long id, Long own) {

        StringBuffer sb = new StringBuffer();




        JpaResultMapper result = new JpaResultMapper();
       // List<FollowDto> followDtos =  result.list(query, FollowDto.class);

        return null;
        //return followDtos;
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
