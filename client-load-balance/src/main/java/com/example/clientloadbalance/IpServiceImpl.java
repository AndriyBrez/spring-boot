package com.example.clientloadbalance;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andriy Brezetskyy
 */
@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private IpStatisticRepository repository;

    @Override
    public List<String> getIps() {
        return repository.getIps();
    }

    @Override
    public void clear() {
        repository.clear();

    }

    @Override
    //@Scheduled(fixedDelay = 10000, initialDelayString = "30000")

    @HystrixCommand(fallbackMethod = "defaultIp1")
    public String call() {
        return repository.call();
    }

    boolean useDefault2 = false;

    @HystrixCommand(fallbackMethod = "defaultIp2")
    public String defaultIp1(){
        if (useDefault2){
            useDefault2 = false;
            throw new RuntimeException("from defalutIp1");
        }else {
            useDefault2 = true;
            return "DEFAULT_IP_1";
        }

    }

    public String defaultIp2(){
        return "DEFAULT_IP_2";
    }
}
