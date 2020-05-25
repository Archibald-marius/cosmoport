package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipDTO;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping("/rest/ships")
    public List<Ship> getShips(@RequestParam(value = "name") Optional<String> name,
                               @RequestParam(value = "planet") Optional<String> planet,
                               @RequestParam(value = "shipType") Optional<String> shipType,
                               @RequestParam(value = "after") Optional<Long> after,
                               @RequestParam(value = "before") Optional<Long> before,
                               @RequestParam(value = "isUsed") Optional<Boolean> isUsed,
                               @RequestParam(value = "minSpeed") Optional<Double> minSpeed,
                               @RequestParam(value = "maxSpeed") Optional<Double> maxSpeed,
                               @RequestParam(value = "minCrewSize") Optional<Integer> minCrewSize,
                               @RequestParam(value = "maxCrewSize") Optional<Integer> maxCrewSize,
                               @RequestParam(value = "minRating") Optional<Double> minRating,
                               @RequestParam(value = "maxRating") Optional<Double> maxRating,
                               @RequestParam(value = "order", defaultValue = "ID") Optional<String> order,
                               @RequestParam(value = "pageNumber", defaultValue = "0") Optional<Integer> pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "3") Optional<Integer> pageSize) {
        ShipDTO shipDTO = new ShipDTO(name, planet, shipType, after, before, isUsed, minSpeed,
                maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating, order,
                pageNumber, pageSize);
        return shipService.getShips(shipDTO);
    }

    @GetMapping("/rest/ships/count")
    public Long getShipCount(@RequestParam(value = "name") Optional<String> name,
                             @RequestParam(value = "planet") Optional<String> planet,
                             @RequestParam(value = "shipType") Optional<String> shipType,
                             @RequestParam(value = "after") Optional<Long> after,
                             @RequestParam(value = "before") Optional<Long> before,
                             @RequestParam(value = "isUsed") Optional<Boolean> isUsed,
                             @RequestParam(value = "minSpeed") Optional<Double> minSpeed,
                             @RequestParam(value = "maxSpeed") Optional<Double> maxSpeed,
                             @RequestParam(value = "minCrewSize") Optional<Integer> minCrewSize,
                             @RequestParam(value = "maxCrewSize") Optional<Integer> maxCrewSize,
                             @RequestParam(value = "minRating") Optional<Double> minRating,
                             @RequestParam(value = "maxRating") Optional<Double> maxRating) {
        Optional<String> order = Optional.of("");
        Optional<Integer> pageNumber = Optional.of(Integer.MAX_VALUE);
        Optional<Integer> pageSize = Optional.of(Integer.MAX_VALUE);
        ShipDTO shipDTO = new ShipDTO(name, planet, shipType, after, before, isUsed, minSpeed,
                maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);
        return shipService.shipCount(shipDTO);
    }

    @PostMapping("/rest/ships")
    public Ship addShip(@RequestBody Ship ship) {
        return shipService.createShip(ship);
    }

    @GetMapping("/rest/ships/{id}")
    public Ship getShipById(@PathVariable Long id) {
        return shipService.getShip(id);
    }

    @DeleteMapping("/rest/ships/{id}")
    public void deleteShip(@PathVariable Long id) {
        shipService.deleteShip(id);
    }

    @PostMapping("/rest/ships/{id}")
    public Ship updateShipById(@PathVariable Long id, @RequestBody Ship ship) {
        return shipService.updateShip(id, ship);
    }

}
