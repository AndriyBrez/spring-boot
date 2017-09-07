package com.example.clientloadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andriy_Brezetskyy on 9/7/2017.
 */
@Component
@Configuration
@RibbonClient(name = "ip-service")
public class IpStatisticRepositoryImpl implements IpStatisticRepository {

    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }


    @Autowired
    private RestTemplate restTemplate;

    private List<String> ips = new LinkedList<>();

    @Override
    public List<String> getIps() {
        return ips;
    }

    @Override
    public void clear() {
        ips.clear();
    }


    @Scheduled(fixedDelay = 10000)
    public void call(){
        String ip = restTemplate.getForObject("http://ip-service/ips", String.class);
        ips.add(ip);
    }
}
