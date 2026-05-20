package com.ganesh.Train.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ganesh.Train.repo.TrainRepository;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.List;

@NoArgsConstructor

@ToString
@Getter
@Setter
@Entity
public class Train {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainName;


    private String trainNumber;


    @OneToMany(mappedBy = "train" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JsonBackReference
    List<TrainSchedule> scheduleList;

    public Train(Long id, String trainName, String trainNumber, List<TrainSchedule> scheduleList) {
        this.id = id;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.scheduleList = scheduleList;
    }
}
