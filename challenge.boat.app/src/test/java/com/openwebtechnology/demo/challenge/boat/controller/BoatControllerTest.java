package com.openwebtechnology.demo.challenge.boat.controller;

import com.openwebtechnology.demo.challenge.boat.model.Boat;
import com.openwebtechnology.demo.challenge.boat.repository.BoatRepository;
import com.openwebtechnology.demo.challenge.boat.service.BoatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;


@RunWith(MockitoJUnitRunner.class)
public class BoatControllerTest {

    @Mock
    BoatService service;

    @Mock
    BoatRepository repository;

    @InjectMocks
    private BoatController controller;

    private static final Boat BOAT = new Boat(1,"boat1","description");




    @Test
    public void testGetAllBoats() {
        List<Boat> list= new ArrayList<>();
        list.add(BOAT);
        Mockito.when(service.getAllBoats()).thenReturn(list);
        ResponseEntity<List<Boat>> result=controller.getAllBoats();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testGetAllBoatsIsEmpty() {
        Mockito.when(service.getAllBoats()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Boat>> result=controller.getAllBoats();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testGetAllBoatsError() {
        Mockito.when(service.getAllBoats()).thenReturn(null);
        ResponseEntity<List<Boat>> result=controller.getAllBoats();
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @Test
    public void getBoatById() {
        Mockito.when(service.getBoatById(1l)).thenReturn(BOAT);
        ResponseEntity<Boat> result=controller.getBoatById(1l);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }



    @Test
    public void getBoatByIdNotFound() {
        Mockito.when(service.getBoatById(1l)).thenReturn(null);
        ResponseEntity<Boat> result=controller.getBoatById(1l);
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    public void createBoat() {

        Mockito.when(service.createBoat(BOAT)).thenReturn(BOAT);
        ResponseEntity<Boat> result=controller.createBoat(BOAT);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    @Test
    public void createBoatError() {
        lenient().when(service.createBoat(BOAT)).thenReturn(new Boat());
        ResponseEntity<Boat> result=controller.createBoat(null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Test
    public void updateBoat() {
        Mockito.when(service.updateBoat(1l,BOAT)).thenReturn(BOAT);
        ResponseEntity<Boat> result=controller.updateBoat(1l,BOAT);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void updateBoatError() {
        Mockito.when(service.updateBoat(1l,BOAT)).thenReturn(null);
        ResponseEntity<Boat> result=controller.updateBoat(1l,BOAT);
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }



    @Test
    public void deleteBoat() {
        doNothing().when(service).deleteBoatById(1l);
        ResponseEntity<HttpStatus> result=controller.deleteBoat(1l);
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


    @Test
    public void deleteAllBoats() {
        doNothing().when(service).deleteAllBoats();
        ResponseEntity<HttpStatus> result=controller.deleteAllBoats();
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}