package com.example.clientloadbalance;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andriy Brezetskyy
 */
public interface IpService {


    List<String> getIps();

    void clear();

    String call();

}
