package com.openwebtechnology.demo.challenge.boat.repository;

import com.openwebtechnology.demo.challenge.boat.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {

}