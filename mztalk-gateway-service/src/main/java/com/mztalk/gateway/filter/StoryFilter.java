package com.mztalk.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class StoryFilter extends AbstractGatewayFilterFactory<StoryFilter.StoryConfig> {

    @Autowired
    private  FilterFactory filterFactory;

    public StoryFilter(){super(StoryConfig.class);}


    @Override
    public GatewayFilter apply(StoryConfig config) {
        return ((exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            filterFactory.saveTraffic("story", String.valueOf(uri));
            return chain.filter(exchange);
        });
    }

    public static class StoryConfig{

    }
}
