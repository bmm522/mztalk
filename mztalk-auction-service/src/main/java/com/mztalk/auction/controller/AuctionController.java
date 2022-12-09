package com.mztalk.auction.controller;

import com.mztalk.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auction")
@RestController
public class AuctionController {
    @Autowired
    AuctionService auctionService = new AuctionService();



}
