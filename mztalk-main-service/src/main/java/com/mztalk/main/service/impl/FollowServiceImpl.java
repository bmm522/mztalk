package com.mztalk.main.service.impl;


import com.mztalk.main.domain.dto.FollowDto;
import com.mztalk.main.repository.FollowRepository;
import com.mztalk.main.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    private final EntityManager em;

    //리스트 뽑아주기...
    @Transactional(readOnly = true)
    public List<FollowDto> followDtoList(Long own, Long userno ){

        StringBuffer sb = new StringBuffer();




        return null;
    }



    @Override
    public void follow(Long toUserId, Long userno) {

    }
}
