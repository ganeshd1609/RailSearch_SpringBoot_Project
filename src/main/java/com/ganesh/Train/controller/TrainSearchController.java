package com.ganesh.Train.controller;


import com.ganesh.Train.entity.TrainSchedule;
import com.ganesh.Train.service.TrainSearchService;
import com.ganesh.Train.service.TrainService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@ToString
@RestController
@RequestMapping("/search")
public class TrainSearchController {


    private TrainSearchService trainSearchService;

    public TrainSearchController(TrainSearchService trainSearchService){
        this.trainSearchService = trainSearchService;
    }


    @GetMapping("/byCode")
    public List<TrainSchedule> findTrainByStationCode(@RequestParam String sourceCode, @RequestParam String  destinationCode){

       return trainSearchService.findTrainByStationCode(sourceCode.toString(),destinationCode);
    }


    @GetMapping("/byName")
    public List<TrainSchedule> findTrainByStationName(@RequestParam String sourceStationName, @RequestParam String  destinationStationName){

        return trainSearchService.findTrainByStationName(sourceStationName.toString(),destinationStationName);
    }
}
