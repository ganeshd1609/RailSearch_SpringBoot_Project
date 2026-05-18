package com.ganesh.Train.repo;

import com.ganesh.Train.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule,Long> {
}
