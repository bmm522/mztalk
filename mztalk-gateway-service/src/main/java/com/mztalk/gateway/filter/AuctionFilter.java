package com.mztalk.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class AuctionFilter  extends AbstractGatewayFilterFactory<AuctionFilter.AuctionConfig> {


    @Autowired
    private  FilterFactory filterFactory;

    public AuctionFilter(){super(AuctionConfig.class);}
    @Override
    public GatewayFilter apply(AuctionConfig config) {
        return ((exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            filterFactory.saveTraffic("auction", String.valueOf(uri));
            return chain.filter(exchange);
        });
    }

    public static class AuctionConfig{

    }
}
