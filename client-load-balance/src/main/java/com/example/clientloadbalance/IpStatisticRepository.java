package com.example.clientloadbalance;

import java.util.List;

/**
 * Created by Andriy_Brezetskyy on 9/7/2017.
 */

public interface IpStatisticRepository {

    List<String> getIps();

    void clear();

    void call();
}
