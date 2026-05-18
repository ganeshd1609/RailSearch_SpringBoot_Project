package com.ganesh.Train.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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


    @OneToMany(mappedBy = "Train" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    List<TrainSchedule> scheduleList;


}
