package com.openwebtechnology.demo.challenge.boat.service;

import com.openwebtechnology.demo.challenge.boat.model.Boat;
import com.openwebtechnology.demo.challenge.boat.repository.BoatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class BoatServiceTest {

    @Mock
    private BoatRepository repositoryBoat;

    private static final Boat BOAT = new Boat(1,"boat1","description");
    @InjectMocks
    BoatService boatService;

    @Test
    public void testGetAllBoats() {
        List<Boat> mockedResult = new ArrayList<>();
        Mockito.when(repositoryBoat.findAll()).thenReturn(mockedResult);
        assertNotNull(boatService.getAllBoats());
    }

   @Test
    public void testGetBoatById() {
        Optional<Boat> optMock = Optional.of(BOAT);
        Mockito.when(repositoryBoat.findById(1l)).thenReturn(optMock);
        assertNotNull(boatService.getBoatById(1l));
    }
    @Test
    public void testGetBoatByIdError() {
        Mockito.when(repositoryBoat.findById(1l)).thenReturn(Optional.empty());
        assertNull(boatService.getBoatById(1l));
    }



   @Test
    public void testUpdateBoat(){
        Optional<Boat> optMock = Optional.of(BOAT);
        Mockito.when(repositoryBoat.findById(1l)).thenReturn(optMock);
        Mockito.when(repositoryBoat.save(any(Boat.class))).thenReturn(BOAT);
        assertNotNull(boatService.updateBoat(1,BOAT));
        }


    @Test
    public void testCreateBoat() {
        Mockito.when(repositoryBoat.save(any())).thenReturn(BOAT);
        assertNotNull(boatService.createBoat(BOAT));
    }


    @Test
    public void deleteBoatById() {
        //willDoNothing().given(boatService).deleteBoatById(1l);
        boatService.deleteBoatById(1l);
        Mockito.verify(repositoryBoat).deleteById(any());



    }
 @Test
    public void deleteAllBoats() {
        boatService.deleteAllBoats();
        Mockito.verify(repositoryBoat).deleteAll();
    }

}