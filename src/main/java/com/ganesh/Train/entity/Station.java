package com.ganesh.Train.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;




@NoArgsConstructor
@Entity
public class Station {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String stationname;

    private String stationcode;


    public Station(Long id, String stationname, String stationcode) {
        this.id = id;
        this.stationname = stationname;
        this.stationcode = stationcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getStationcode() {
        return stationcode;
    }

    public void setStationcode(String stationcode) {
        this.stationcode = stationcode;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", stationname='" + stationname + '\'' +
                ", stationcode='" + stationcode + '\'' +
                '}';
    }
}
