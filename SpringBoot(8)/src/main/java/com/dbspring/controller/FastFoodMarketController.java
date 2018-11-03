package com.dbspring.controller;

import com.dbspring.DTO.FastFoodMarketDTO;
import com.dbspring.domain.FastFoodMarket;
import com.dbspring.exceptions.*;
import com.dbspring.service.FastFoodMarketService;
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
public class FastFoodMarketController {
    @Autowired
    FastFoodMarketService fastFoodMarketService;

    @GetMapping(value = "/api/fastfoodmarket/city/{city_id}")
    public ResponseEntity<List<FastFoodMarketDTO>> getFastFastFoodMarketsByCityID(@PathVariable Long city_id) throws NoSuchCityException, NoSuchFastFoodMarketException, NoSuchFastFoodException {
        List<FastFoodMarket> fastFoodMarketList = fastFoodMarketService.getFastFoodMarketByCityId(city_id);

        Link link = linkTo(methodOn(FastFoodMarketController.class).getAllFastFoodMarkets()).withSelfRel();

        List<FastFoodMarketDTO> fastFoodMarketsDTO = new ArrayList<>();
        for (FastFoodMarket entity : fastFoodMarketList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FastFoodMarketDTO dto = new FastFoodMarketDTO(entity, selfLink);
            fastFoodMarketsDTO.add(dto);
        }

        return new ResponseEntity<>(fastFoodMarketsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/fastfoodmarket/{fastfoodmarket_id}")
    public ResponseEntity<FastFoodMarketDTO> getFastFoodMarket(@PathVariable Long fastfoodmarket_id) throws NoSuchFastFoodMarketException, NoSuchFastFoodException {
        FastFoodMarket fastfoodmarket = fastFoodMarketService.getFastFoodMarket(fastfoodmarket_id);
        Link link = linkTo(methodOn(FastFoodMarketController.class).getFastFoodMarket(fastfoodmarket_id)).withSelfRel();

        FastFoodMarketDTO fastFoodMarketDTO = new FastFoodMarketDTO(fastfoodmarket, link);

       return new ResponseEntity<>(fastFoodMarketDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/fastfoodmarket")
    public ResponseEntity<List<FastFoodMarketDTO>> getAllFastFoodMarkets() throws NoSuchFastFoodMarketException, NoSuchFastFoodException {
        List<FastFoodMarket> fastFoodMarketList = fastFoodMarketService.getAllFastFoodMarkets();
        Link link = linkTo(methodOn(FastFoodMarketController.class).getAllFastFoodMarkets()).withSelfRel();

        List<FastFoodMarketDTO> fastFoodMarketsDTO = new ArrayList<>();
        for (FastFoodMarket entity : fastFoodMarketList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FastFoodMarketDTO dto = new FastFoodMarketDTO(entity, selfLink);
            fastFoodMarketsDTO.add(dto);
        }

        return new ResponseEntity<>(fastFoodMarketsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/fastfoodmarket/fastfood/{fastfood_id}")
    public ResponseEntity<List<FastFoodMarketDTO>> getFastFoodMarketsByFastFoodID(@PathVariable Long fastfood_id) throws NoSuchFastFoodException, NoSuchFastFoodMarketException {
        Set<FastFoodMarket> fastFoodMarketList = fastFoodMarketService.getFastFoodMarketsByFastFoodId(fastfood_id);
        Link link = linkTo(methodOn(FastFoodMarketController.class).getAllFastFoodMarkets()).withSelfRel();

        List<FastFoodMarketDTO> fastFoodMarketsDTO = new ArrayList<>();
        for (FastFoodMarket entity : fastFoodMarketList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FastFoodMarketDTO dto = new FastFoodMarketDTO(entity, selfLink);
            fastFoodMarketsDTO.add(dto);
        }

        return new ResponseEntity<>(fastFoodMarketsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/fastfoodmarket/city/{city_id}")
    public  ResponseEntity<FastFoodMarketDTO> addFastFoodMarket(@RequestBody FastFoodMarket newFastFoodMarket, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchFastFoodMarketException, NoSuchFastFoodException {
        fastFoodMarketService.createFastFoodMarket(newFastFoodMarket, city_id);
        Link link = linkTo(methodOn(FastFoodMarketController.class).getFastFoodMarket(newFastFoodMarket.getId())).withSelfRel();

        FastFoodMarketDTO fastFoodMarketDTO = new FastFoodMarketDTO(newFastFoodMarket, link);

        return new ResponseEntity<>(fastFoodMarketDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/fastfoodmarket/{fastfoodmarket_id}/city/{city_id}")
    public  ResponseEntity<FastFoodMarketDTO> updateFastFoodMarket(@RequestBody FastFoodMarket uFastFoodMarket,
                                                   @PathVariable Long fastfoodmarket_id, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchFastFoodMarketException, NoSuchFastFoodException {
        fastFoodMarketService.updateFastFoodMarket(uFastFoodMarket, fastfoodmarket_id, city_id);
        FastFoodMarket fastfoodmarket =fastFoodMarketService.getFastFoodMarket(fastfoodmarket_id);
        Link link = linkTo(methodOn(FastFoodMarketController.class).getFastFoodMarket(fastfoodmarket_id)).withSelfRel();

        FastFoodMarketDTO fastFoodMarketDTO = new FastFoodMarketDTO(fastfoodmarket, link);

        return new ResponseEntity<>(fastFoodMarketDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/fastfoodmarket/{fastfoodmarket_id}")
    public  ResponseEntity deleteFastFoodMarket(@PathVariable Long fastfoodmarket_id) throws NoSuchFastFoodMarketException, ExistsFastFoodMarketForFastFoodException, ExistsFastFoodsForFastFoodMarketException {
        fastFoodMarketService.deleteFastFoodMarket(fastfoodmarket_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/fastfoodmarket/{fastfoodmarket_id}/fastfood/{fastfood_id}")
    public  ResponseEntity<FastFoodMarketDTO> addFastFoodForFastFoodMarket(@PathVariable Long fastfoodmarket_id, @PathVariable Long fastfood_id)
            throws NoSuchFastFoodMarketException, NoSuchFastFoodException, AlreadyExistsFastFoodInFastFoodMarketException, FastFoodAbsentException {
        fastFoodMarketService.addFastFoodForFastFoodMarket(fastfoodmarket_id,fastfood_id);
        FastFoodMarket fastfoodmarket = fastFoodMarketService.getFastFoodMarket(fastfoodmarket_id);
        Link link = linkTo(methodOn(FastFoodMarketController.class).getFastFoodMarket(fastfoodmarket_id)).withSelfRel();

        FastFoodMarketDTO fastFoodMarketDTO = new FastFoodMarketDTO(fastfoodmarket, link);

        return new ResponseEntity<>(fastFoodMarketDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/fastfoodmarket/{fastfoodmarket_id}/fastfood/{fastfood_id}")
    public  ResponseEntity<FastFoodMarketDTO> removeFastFoodForFastFoodMarket(@PathVariable Long fastfoodmarket_id, @PathVariable Long fastfood_id)
            throws NoSuchFastFoodMarketException, NoSuchFastFoodException, FastFoodMarketHasNotFastFoodException {
        fastFoodMarketService.removeFastFoodForFastFoodMarket(fastfoodmarket_id,fastfood_id);
        FastFoodMarket fastfoodmarket = fastFoodMarketService.getFastFoodMarket(fastfoodmarket_id);
        Link link = linkTo(methodOn(FastFoodMarketController.class).getFastFoodMarket(fastfoodmarket_id)).withSelfRel();

        FastFoodMarketDTO fastFoodMarketDTO = new FastFoodMarketDTO(fastfoodmarket, link);

        return new ResponseEntity<>(fastFoodMarketDTO, HttpStatus.OK);
    }

}
