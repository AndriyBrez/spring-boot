package com.example.clientloadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private boolean throwExeption = false;

    @Override
    public String call(){
        if (throwExeption){
            throwExeption = false;
            throw  new RuntimeException("doesnt call");
        }else {
            throwExeption = true;
        }

        //String ip = restTemplate.getForObject("http://ip-service/ips", String.class);
        String ip = "from the repository";
        ips.add(ip);
        return ip;
    }
}
