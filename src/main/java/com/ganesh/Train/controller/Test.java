package com.ganesh.Train.controller;


import com.ganesh.Train.entity.Station;
import com.ganesh.Train.entity.Train;
import com.ganesh.Train.entity.TrainSchedule;
import com.ganesh.Train.repo.StationRepository;
import com.ganesh.Train.repo.TrainRepository;
import com.ganesh.Train.repo.TrainScheduleRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;


    @Autowired
     TrainScheduleRepository trainScheduleRepository;

    @GetMapping
    public void test(){
        Station delhi = new Station(null , "newdehli","NDLS");
        Station mumbai = new Station(null , "mumbai","CST");
        Station kolkata = new Station(null , "kolkata","KOAA");
        Station chennai = new Station(null , "chennai","MAS");
        Station pune = new Station(null , "pune","PNU");
        Station varanasi = new Station(null , "varanasi","BNS");
        Station banglore = new Station(null , "banglore","SBC");

        stationRepository.saveAll(List.of(delhi,mumbai,kolkata,chennai,pune,varanasi,banglore));

        Train Rajdhadni= new Train(null , "Rajdhani exp" ,"12306" ,null);
        Train duranto= new Train(null , "duranto exp" ,"12304" ,null);
        Train intercity= new Train(null , "intercity exp" ,"12305" ,null);
        Train shatabdi= new Train(null , "shatabdi exp" ,"12303" ,null);
        Train udyan= new Train(null , "udyan exp" ,"12302" ,null);
        Train vishakapatnam= new Train(null , "vishakapatnam exp" ,"12301" ,null);

        trainRepository.saveAll(List.of(Rajdhadni,duranto,intercity,shatabdi,udyan,vishakapatnam));

        TrainSchedule sc1 = new TrainSchedule(null, Rajdhadni,delhi,mumbai,"6:00","14:00");
        TrainSchedule sc2 = new TrainSchedule(null, duranto,delhi,pune,"6:00","18:00");
        TrainSchedule sc3 = new TrainSchedule(null, intercity,pune,mumbai,"6:00","22:00");
        TrainSchedule sc4 = new TrainSchedule(null, shatabdi,varanasi,mumbai,"14:00","18:00");
        TrainSchedule sc5 = new TrainSchedule(null, vishakapatnam,delhi,banglore,"6:00","6:00");





        trainScheduleRepository.saveAll(List.of(sc1,sc2,sc3,sc4));

        System.out.println("data inserted in db ");







    }
}
