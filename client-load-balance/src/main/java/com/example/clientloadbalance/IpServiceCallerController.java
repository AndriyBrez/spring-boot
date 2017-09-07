package com.example.clientloadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andriy_Brezetskyy on 9/7/2017.
 */
@RestController
public class IpServiceCallerController {

    @Autowired
    private IpStatisticRepository ipStatistic;



    @GetMapping("stat")
    public List<String> getIps() {
        return ipStatistic.getIps();
    }

    @GetMapping("clear")
    public void cleanStatisic() {
        ipStatistic.clear();
    }

    @GetMapping("call")
    public void call() {
        ipStatistic.call();
    }


}
