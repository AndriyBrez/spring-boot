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
    private IpService service;



    @GetMapping("stat")
    public List<String> getIps() {
        return service.getIps();
    }

    @GetMapping("clear")
    public void cleanStatisic() {
        service.clear();
    }

    @GetMapping("call")
    public String call()  {
        return service.call();
    }


}
