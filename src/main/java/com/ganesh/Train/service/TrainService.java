package com.ganesh.Train.service;


import com.ganesh.Train.entity.Train;
import com.ganesh.Train.repo.TrainRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class TrainService {










    private TrainRepository trainRepository;

    public List<Train> getAllTrains() {

       return   trainRepository.findAll();

    }


    public void addTrain() {

    }
}
