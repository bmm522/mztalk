package com.mztalk.gateway.domain.dto;

import lombok.Getter;
import lombok.Setter;

public interface TrafficOfRequestTimeDto {

    long getCount();
    String getRequestTime();
    String getServiceName();
}
