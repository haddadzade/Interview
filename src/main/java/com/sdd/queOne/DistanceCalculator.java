package com.sdd.queOne;

import com.sdd.queOne.service.impl.DistanceServiceImpl;

import java.io.IOException;

public class DistanceCalculator {

    public static void main(String[] args)  {

        DistanceServiceImpl serviceObj = new DistanceServiceImpl();
        serviceObj.readFile();
    }
}