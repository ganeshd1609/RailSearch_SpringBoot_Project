package com.ganesh.Train.entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class TrainSchedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;



    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station source;


    @ManyToOne
    @JoinColumn(name = "destination_station_id")
    private Station destination;


    private String arrivalTime;
    private String departureTime;
}
