package com.mztalk.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.net.URI;


@Component
public class BungFilter extends AbstractGatewayFilterFactory<BungFilter.BungConfig> {

    @Autowired
    private  FilterFactory filterFactory;

    public BungFilter(){super(BungConfig.class);}

    @Override
    public GatewayFilter apply(BungConfig config) {
        return ((exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            filterFactory.saveTraffic("bung", String.valueOf(uri));
            return chain.filter(exchange);
        });
    }

    public static class BungConfig{

    }
}
