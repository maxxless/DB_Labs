package com.dbspring.controller;

import com.dbspring.DTO.CityDTO;
import com.dbspring.domain.City;
import com.dbspring.exceptions.ExistsFastFoodMarketsForCityException;
import com.dbspring.exceptions.NoSuchFastFoodException;
import com.dbspring.exceptions.NoSuchCityException;
import com.dbspring.exceptions.NoSuchFastFoodMarketException;
import com.dbspring.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping(value = "/api/city")
    public ResponseEntity<List<CityDTO>> getAllCities() throws NoSuchFastFoodMarketException, NoSuchFastFoodException, NoSuchCityException {
        List<City> cityList = cityService.getAllCity();
        Link link = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();

        List<CityDTO> citiesDTO = new ArrayList<>();
        for (City entity : cityList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CityDTO dto = new CityDTO(entity, selfLink);
            citiesDTO.add(dto);
        }

        return new ResponseEntity<>(citiesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/city/{city_id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Long city_id) throws NoSuchCityException, NoSuchFastFoodMarketException, NoSuchFastFoodException {
        City city = cityService.getCity(city_id);
        Link link = linkTo(methodOn(CityController.class).getCity(city_id)).withSelfRel();

        CityDTO cityDTO = new CityDTO(city, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/city/{city_id}")
    public  ResponseEntity<CityDTO> addCity(@RequestBody City newCity) throws NoSuchCityException, NoSuchFastFoodMarketException, NoSuchFastFoodException {
        cityService.createCity(newCity);
        Link link = linkTo(methodOn(CityController.class).getCity(newCity.getId())).withSelfRel();

        CityDTO cityDTO = new CityDTO(newCity, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/city/{city_id}")
    public  ResponseEntity<CityDTO> updateCity(@RequestBody City ucity, @PathVariable Long city_id) throws NoSuchCityException, NoSuchFastFoodMarketException, NoSuchFastFoodException {
        cityService.updateCity(ucity, city_id);
        City city = cityService.getCity(city_id);
        Link link = linkTo(methodOn(CityController.class).getCity(city_id)).withSelfRel();

        CityDTO cityDTO = new CityDTO(city, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/city/{city_id}")
    public  ResponseEntity deleteCity(@PathVariable Long city_id) throws NoSuchCityException, ExistsFastFoodMarketsForCityException {
        cityService.deleteCity(city_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
