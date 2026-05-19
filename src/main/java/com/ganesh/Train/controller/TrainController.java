package com.ganesh.Train.controller;


import com.ganesh.Train.entity.Train;
import com.ganesh.Train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/trains")
@RestController
public class TrainController {


   private TrainService trainService;
   public TrainController(TrainService trainService){
       this.trainService=trainService;
   }

   @GetMapping
   public List<Train> getAllTrains(){

      return trainService.getAllTrains();
   }

   @PostMapping
   public Train addTrain(@RequestBody Train train){
      return trainService.addTrain(train);
   }








}
