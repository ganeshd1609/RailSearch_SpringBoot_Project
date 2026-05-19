package com.ganesh.Train.service;

import com.ganesh.Train.entity.TrainSchedule;
import com.ganesh.Train.repo.TrainScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TrainSearchService {

    @Autowired
    private TrainScheduleRepository trainScheduleRepository;




    public List<TrainSchedule> findTrainByStationCode(String sourcecode, String destinationcode) {
       return trainScheduleRepository.findBySource_StationcodeAndDestination_Stationcode(sourcecode,destinationcode);
    }

    public List<TrainSchedule> findTrainByStationName(String sourceStationName, String destinationStationname) {

        return trainScheduleRepository.findBySource_StationnameAndDestination_Stationname(sourceStationName,destinationStationname);
    }
}
