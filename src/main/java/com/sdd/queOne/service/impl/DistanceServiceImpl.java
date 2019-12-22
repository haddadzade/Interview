package com.sdd.queOne.service.impl;

import com.google.gson.Gson;
import com.sdd.queOne.service.DistanceService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DistanceServiceImpl implements DistanceService {

    @Override
    public double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        distance = Math.pow(distance, 2) + Math.pow(0, 2);

        return Math.sqrt(distance);
    }

    @Override
    public void readFile() {

        File file = new File(getClass().getClassLoader().getResource("employee.csv").getFile());

        //BufferedReader br = null;
        String cvsSplitBy = ",";
        String line = "";
        List<String> invitedEmployeesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] employeesLatLon = line.split(cvsSplitBy);
                if (distance(35.745108, Double.parseDouble(employeesLatLon[0]), 51.451365, Double.parseDouble(employeesLatLon[1])) < 50)
                    invitedEmployeesList.add(employeesLatLon[2]);

            }
            Gson gson = new Gson();
            invitedEmployeesList.stream().forEach(s -> System.out.println(gson.toJson(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
