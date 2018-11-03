package com.dbspring.DTO;

import com.dbspring.controller.FastFoodMarketController;
import com.dbspring.domain.City;
import com.dbspring.exceptions.NoSuchFastFoodException;
import com.dbspring.exceptions.NoSuchCityException;
import com.dbspring.exceptions.NoSuchFastFoodMarketException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CityDTO extends ResourceSupport {
    City city;
    public CityDTO(City city, Link selfLink) throws NoSuchFastFoodMarketException, NoSuchFastFoodException, NoSuchCityException {
        this.city=city;
        add(selfLink);
        add(linkTo(methodOn(FastFoodMarketController.class).getFastFastFoodMarketsByCityID(city.getId())).withRel("fastfoods"));
    }

    public Long getCityId() { return city.getId(); }

    public String getCity() {
        return city.getCity();
    }
}
