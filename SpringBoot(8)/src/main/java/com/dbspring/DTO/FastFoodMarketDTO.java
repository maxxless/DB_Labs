package com.dbspring.DTO;

import com.dbspring.controller.FastFoodController;
import com.dbspring.domain.FastFoodMarket;
import com.dbspring.exceptions.NoSuchFastFoodException;
import com.dbspring.exceptions.NoSuchFastFoodMarketException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class FastFoodMarketDTO extends ResourceSupport {
    FastFoodMarket fastFoodMarket;
    public FastFoodMarketDTO(FastFoodMarket fastFoodMarket, Link selfLink) throws NoSuchFastFoodMarketException, NoSuchFastFoodException {
        this.fastFoodMarket=fastFoodMarket;
        add(selfLink);
        add(linkTo(methodOn(FastFoodController.class).getFastFoodsByFastFoodMarketID(fastFoodMarket.getId())).withRel("fastfoods"));
    }

    public Long getFastFoodMarketId() {
        return fastFoodMarket.getId();
    }

    public String getSurname() {
        return fastFoodMarket.getSurname();
    }

    public String getName() {
        return fastFoodMarket.getName();
    }

    public String getEmail() {
        return fastFoodMarket.getEmail();
    }

    public String getCity() {
        if(fastFoodMarket.getCity()==null) return "";
        return fastFoodMarket.getCity().getCity();
    }

    public String getStreet() {
        return fastFoodMarket.getStreet();
    }

    public String getApartment() {
        return fastFoodMarket.getApartment();
    }
}
