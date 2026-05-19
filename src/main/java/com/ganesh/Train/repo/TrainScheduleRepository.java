package com.ganesh.Train.repo;

import com.ganesh.Train.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule,Long> {

    List<TrainSchedule> findBySource_StationcodeAndDestination_Stationcode(String  sourcecode,String destinationcode);

    List<TrainSchedule> findBySource_StationnameAndDestination_Stationname(String sourceStationname,String destinationStationname);
}
