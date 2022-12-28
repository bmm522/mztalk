package com.mztalk.main.domain.serviceinfo.service.impl;


import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.repository.BoardRepository;
import com.mztalk.main.domain.serviceinfo.entity.ServiceInfo;
import com.mztalk.main.domain.serviceinfo.repository.ServiceInfoRepository;
import com.mztalk.main.domain.serviceinfo.service.ServiceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceInfoServiceImpl implements ServiceInfoService {

    private final ServiceInfoRepository serviceInfoRepository;

    private final BoardRepository boardRepository;

    @Override
    public void mentorsSubscribe(Long own) {

        ServiceInfo serviceInfo = serviceInfoRepository.findById(own).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));

        serviceInfo.changeMStatus();

    }

    @Override
    public void mentorsUnSubscribe(Long own) {

        ServiceInfo serviceInfo = serviceInfoRepository.findById(own).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));

        serviceInfo.changeUnMStatus();
    }

    @Override
    public void bungSubscribe(Long own) {

        ServiceInfo serviceInfo = serviceInfoRepository.findById(own).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));

        serviceInfo.changeBStatus();
    }

    @Override
    public void bungUnSubscribe(Long own) {

        ServiceInfo serviceInfo = serviceInfoRepository.findById(own).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));

        serviceInfo.changeUnBStatus();
    }

    @Override
    public void auctionSubscribe(Long own) {

        ServiceInfo serviceInfo = serviceInfoRepository.findById(own).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));

        serviceInfo.changeAStatus();
    }

    @Override
    public void auctionUnSubscribe(Long own) {

        ServiceInfo serviceInfo = serviceInfoRepository.findById(own).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));

        serviceInfo.changeUnAStatus();

    }
}
