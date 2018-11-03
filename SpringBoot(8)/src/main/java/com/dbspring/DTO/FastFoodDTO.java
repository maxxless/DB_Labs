package com.dbspring.DTO;

import com.dbspring.controller.FastFoodMarketController;
import com.dbspring.domain.FastFood;
import com.dbspring.exceptions.NoSuchFastFoodException;

import com.dbspring.exceptions.NoSuchFastFoodMarketException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class FastFoodDTO extends ResourceSupport {
    FastFood fastFood;
    public FastFoodDTO(FastFood fastFood, Link selfLink) throws NoSuchFastFoodException, NoSuchFastFoodMarketException {
        this.fastFood=fastFood;
        add(selfLink);
        add(linkTo(methodOn(FastFoodMarketController.class).getFastFoodMarketsByFastFoodID(fastFood.getId())).withRel("fastfoods"));
    }

    public Long getFastFoodId() {
        return fastFood.getId();
    }

    public String getFastFoodName() {
        return fastFood.getFastFoodName();
    }

    public String getAuthor() {
        return fastFood.getAuthor();
    }

    public String getSeller() {
        return fastFood.getSeller();
    }

    public Integer getYearOfCreating() {
        return fastFood.getYearOfCreating();
    }

    public Integer getAmount() {
        return fastFood.getAmount();
    }
}
