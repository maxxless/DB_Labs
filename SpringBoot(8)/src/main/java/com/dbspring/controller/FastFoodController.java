package com.dbspring.controller;


import com.dbspring.DTO.FastFoodDTO;
import com.dbspring.domain.FastFood;
import com.dbspring.exceptions.ExistsFastFoodMarketForFastFoodException;
import com.dbspring.exceptions.NoSuchFastFoodException;
import com.dbspring.exceptions.NoSuchFastFoodMarketException;
import com.dbspring.service.FastFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FastFoodController {
    @Autowired
    FastFoodService fastFoodService;

    @GetMapping(value = "/api/fastfood/fastfoodmarket/{fastfoodmarket_id}")
    public ResponseEntity<List<FastFoodDTO>> getFastFoodsByFastFoodMarketID(@PathVariable Long fastfoodmarket_id) throws NoSuchFastFoodMarketException, NoSuchFastFoodException {
        Set<FastFood> fastFoodList = fastFoodService.getFastFoodsByFastFoodMarketId(fastfoodmarket_id);
        Link link = linkTo(methodOn(FastFoodController.class).getAllFastFoods()).withSelfRel();

        List<FastFoodDTO> fastFoodsDTO = new ArrayList<>();
        for (FastFood entity : fastFoodList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FastFoodDTO dto = new FastFoodDTO(entity, selfLink);
            fastFoodsDTO.add(dto);
        }

        return new ResponseEntity<>(fastFoodsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/fastfood/{fastfood_id}")
    public ResponseEntity<FastFoodDTO> getFastFood(@PathVariable Long fastfood_id) throws NoSuchFastFoodException, NoSuchFastFoodMarketException {
        FastFood fastfood = fastFoodService.getFastFood(fastfood_id);
        Link link = linkTo(methodOn(FastFoodController.class).getFastFood(fastfood_id)).withSelfRel();

        FastFoodDTO fastFoodDTO = new FastFoodDTO(fastfood, link);

        return new ResponseEntity<>(fastFoodDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/fastfood")
    public ResponseEntity<List<FastFoodDTO>> getAllFastFoods() throws NoSuchFastFoodException, NoSuchFastFoodMarketException {
        List<FastFood> fastFoodList = fastFoodService.getAllFastFoods();
        Link link = linkTo(methodOn(FastFoodController.class).getAllFastFoods()).withSelfRel();

        List<FastFoodDTO> fastFoodsDTO = new ArrayList<>();
        for (FastFood entity : fastFoodList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FastFoodDTO dto = new FastFoodDTO(entity, selfLink);
            fastFoodsDTO.add(dto);
        }

        return new ResponseEntity<>(fastFoodsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/fastfood")
    public ResponseEntity<FastFoodDTO> addFastFood(@RequestBody FastFood newFastFood) throws NoSuchFastFoodException, NoSuchFastFoodMarketException {
        fastFoodService.createFastFood(newFastFood);
        Link link = linkTo(methodOn(FastFoodController.class).getFastFood(newFastFood.getId())).withSelfRel();

        FastFoodDTO fastFoodDTO = new FastFoodDTO(newFastFood, link);

        return new ResponseEntity<>(fastFoodDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/fastfood/{fastfood_id}")
    public ResponseEntity<FastFoodDTO> updateFastFoodMarket(@RequestBody FastFood uFastFood, @PathVariable Long fastfood_id) throws NoSuchFastFoodException, NoSuchFastFoodMarketException {
        fastFoodService.updateFastFood(uFastFood, fastfood_id);
        FastFood fastfood = fastFoodService.getFastFood(fastfood_id);
        Link link = linkTo(methodOn(FastFoodController.class).getFastFood(fastfood_id)).withSelfRel();

        FastFoodDTO fastFoodDTO = new FastFoodDTO(fastfood, link);

        return new ResponseEntity<>(fastFoodDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/fastfood/{fastfood_id}")
    public  ResponseEntity deleteFastFood(@PathVariable Long fastfood_id) throws ExistsFastFoodMarketForFastFoodException, NoSuchFastFoodException {
        fastFoodService.deleteFastFood(fastfood_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
