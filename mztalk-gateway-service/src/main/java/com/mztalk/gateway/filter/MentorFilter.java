package com.mztalk.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class MentorFilter extends AbstractGatewayFilterFactory<MentorFilter.MentorConfig> {

    @Autowired
    private  FilterFactory filterFactory;

    public MentorFilter(){super(MentorConfig.class);}

    @Override
    public GatewayFilter apply(MentorConfig config) {
        return ((exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            filterFactory.saveTraffic("mentor", String.valueOf(uri));
            return chain.filter(exchange);
        });
    }

    public static class MentorConfig{

    }
}
