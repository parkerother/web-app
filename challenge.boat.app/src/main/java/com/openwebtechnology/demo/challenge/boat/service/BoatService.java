package com.openwebtechnology.demo.challenge.boat.service;

import com.openwebtechnology.demo.challenge.boat.model.Boat;
import com.openwebtechnology.demo.challenge.boat.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {
    @Autowired
    private BoatRepository boatRepository;



    public Boat getBoatById(Long id) {
        Optional<Boat> boatData = boatRepository.findById(id);
        return (boatData.isPresent()) ? boatData.get() : null;

    }

    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    public Boat updateBoat(long id, Boat Boat) {
        Boat boatUp = null;
        Optional<Boat> boatData = boatRepository.findById(id);
        if (boatData.isPresent()) {
            Boat boat = boatData.get();
            boat.setName(Boat.getName());
            boat.setDescription(Boat.getDescription());
            boatUp = boatRepository.save(boat);
        }
        return boatUp;

    }
    public Boat createBoat(Boat Boat) {
            return boatRepository.save(new Boat(Boat.getName(), Boat.getDescription()));
         }


    public void deleteBoatById(Long id) {
        boatRepository.deleteById(id);
    }

    public void deleteAllBoats() {
        boatRepository.deleteAll();
    }
}