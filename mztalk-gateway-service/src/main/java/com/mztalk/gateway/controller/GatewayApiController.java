package com.mztalk.gateway.controller;

import com.mztalk.gateway.domain.Result;
import com.mztalk.gateway.service.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class GatewayApiController {


    @GetMapping("/traffic")
    public Result<?> getTotalCount(@RequestParam("sixBefore")String sixBefore,
                                   @RequestParam("fiveBefore")String fiveBefore,
                                   @RequestParam("fourBefore")String fourBefore,
                                   @RequestParam("threeBefore")String threeBefore,
                                   @RequestParam("twoBefore")String twoBefore,
                                   @RequestParam("oneBefore")String oneBefore,
                                   @RequestParam("today")String today){
        return trafficService.getTotalCount(sixBefore, fiveBefore, fourBefore, threeBefore, twoBefore, oneBefore, today);
    }

    private final TrafficService trafficService;
    @GetMapping("/traffic/{serviceName}")
    public Result<?> getTrafficOfServiceName(@PathVariable("serviceName")String serviceName){
        System.out.println(serviceName);
        return trafficService.getTrafficOfServiceName(serviceName);
    }

    @GetMapping("/daily-traffic")
    public Result<?> getTrafficOfRequestTime(@RequestParam("requestTime")String requestTime){
        return trafficService.getTrafficOfServiceNameAndRequestTime(requestTime);
    }
}
