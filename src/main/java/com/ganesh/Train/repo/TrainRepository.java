package com.ganesh.Train.repo;

import com.ganesh.Train.entity.Train;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {


}
