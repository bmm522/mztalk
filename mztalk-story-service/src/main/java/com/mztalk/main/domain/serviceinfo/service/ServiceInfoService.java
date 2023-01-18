package com.mztalk.main.domain.serviceinfo.service;



public interface ServiceInfoService {

    void mentorsSubscribe(Long own);

    void mentorsUnSubscribe(Long own);

    void bungSubscribe(Long own);

    void bungUnSubscribe(Long own);

    void auctionSubscribe(Long own);

    void auctionUnSubscribe(Long own);


}
