package com.ganesh.Train.service;


import com.ganesh.Train.entity.Train;
import com.ganesh.Train.repo.TrainRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class TrainService {



    @Autowired
    private TrainRepository trainRepository;

    public List<Train> getAllTrains() {

       return   trainRepository.findAll();

    }


    public Train addTrain(Train train) {
        return trainRepository.save(train);

    }
}
