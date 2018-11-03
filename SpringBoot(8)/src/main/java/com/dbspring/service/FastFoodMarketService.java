package com.dbspring.service;

import com.dbspring.Repository.FastFoodRepository;
import com.dbspring.Repository.CityRepository;
import com.dbspring.Repository.FastFoodMarketRepository;
import com.dbspring.domain.FastFood;
import com.dbspring.domain.City;
import com.dbspring.domain.FastFoodMarket;
import com.dbspring.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class FastFoodMarketService {
    @Autowired
    FastFoodMarketRepository fastFoodMarketRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    FastFoodRepository fastFoodRepository;

    public List<FastFoodMarket> getFastFoodMarketByCityId(Long city_id) throws NoSuchCityException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city == null) throw new NoSuchCityException();
        return city.getFastFoodMarkets();
    }

    public FastFoodMarket getFastFoodMarket(Long fastfoodmarket_id) throws NoSuchFastFoodMarketException {
//        FastFoodMarket fastFood = fastFoodMarketRepository.findOne(fastfoodmarket_id);//1.5.9
        FastFoodMarket fastFood = fastFoodMarketRepository.findById(fastfoodmarket_id).get();//2.0.0.M7
        if (fastFood == null) throw new NoSuchFastFoodMarketException();
        return fastFood;
    }

    public List<FastFoodMarket> getAllFastFoodMarkets() {
        return fastFoodMarketRepository.findAll();
    }

    public Set<FastFoodMarket> getFastFoodMarketsByFastFoodId(Long fastfood_id) throws NoSuchFastFoodException {
//        FastFood fastFood = fastFoodRepository.findOne(fastfood_id);//1.5.9
        FastFood fastFood = fastFoodRepository.findById(fastfood_id).get();//2.0.0.M7
        if (fastFood == null) throw new NoSuchFastFoodException();
        return fastFood.getFastFoodMarkets();
    }

    @Transactional
    public void createFastFoodMarket(FastFoodMarket fastFood, Long city_id) throws NoSuchCityException {
        if (city_id > 0) {
//            City city = cityRepository.findOne(city_id);//1.5.9
            City city = cityRepository.findById(city_id).get();//2.0.0.M7
            if (city == null) throw new NoSuchCityException();
            fastFood.setCity(city);
        }
        fastFoodMarketRepository.save(fastFood);
    }

    @Transactional
    public void updateFastFoodMarket(FastFoodMarket uFastFoodMarket, Long fastfoodmarket_id, Long city_id) throws NoSuchCityException, NoSuchFastFoodMarketException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city_id > 0) {
            if (city == null) throw new NoSuchCityException();
        }
//        FastFoodMarket fastFood = fastFoodMarketRepository.findOne(fastfoodmarket_id);//1.5.9
        FastFoodMarket fastFood = fastFoodMarketRepository.findById(fastfoodmarket_id).get();//2.0.0.M7
        if (fastFood == null) throw new NoSuchFastFoodMarketException();
        //update
        fastFood.setSurname(uFastFoodMarket.getSurname());
        fastFood.setName(uFastFoodMarket.getName());
        fastFood.setEmail(uFastFoodMarket.getEmail());
        if (city_id > 0) fastFood.setCity(city);
        else fastFood.setCity(null);
        fastFood.setStreet(uFastFoodMarket.getStreet());
        fastFood.setApartment(uFastFoodMarket.getApartment());
        fastFoodMarketRepository.save(fastFood);
    }

    @Transactional
    public void deleteFastFoodMarket(Long fastfoodmarket_id) throws NoSuchFastFoodMarketException, ExistsFastFoodsForFastFoodMarketException {
//        FastFoodMarket fastFood = fastFoodMarketRepository.findOne(fastfoodmarket_id);//1.5.9
        FastFoodMarket fastFood = fastFoodMarketRepository.findById(fastfoodmarket_id).get();//2.0.0.M7
        if (fastFood == null) throw new NoSuchFastFoodMarketException();
        if (fastFood.getFastFoodMarkets().size() != 0) throw new ExistsFastFoodsForFastFoodMarketException();
        fastFoodMarketRepository.delete(fastFood);
    }

    @Transactional
    public void addFastFoodForFastFoodMarket(Long fastfoodmarket_id, Long fastfood_id)
            throws NoSuchFastFoodMarketException, NoSuchFastFoodException, AlreadyExistsFastFoodInFastFoodMarketException, FastFoodAbsentException {
//        FastFoodMarket fastFood = fastFoodMarketRepository.findOne(fastfoodmarket_id);//1.5.9
        FastFoodMarket fastFoodMarket = fastFoodMarketRepository.findById(fastfoodmarket_id).get();//2.0.0.M7
        if (fastFoodMarket == null) throw new NoSuchFastFoodMarketException();
//        FastFood fastFood = fastFoodRepository.findOne(fastfood_id);//1.5.9
        FastFood fastFood = fastFoodRepository.findById(fastfood_id).get();//2.0.0.M7
        if (fastFood == null) throw new NoSuchFastFoodException();
        if (fastFood.getFastFoodMarkets().contains(fastFood) == true) throw new AlreadyExistsFastFoodInFastFoodMarketException();
        if (fastFood.getAmount() <= fastFood.getFastFoodMarkets().size()) throw new FastFoodAbsentException();
        fastFood.getFastFoodMarkets().add(fastFoodMarket);
        fastFoodMarketRepository.save(fastFoodMarket);
    }

    @Transactional
    public void removeFastFoodForFastFoodMarket(Long fastfoodmarket_id, Long fastfood_id)
            throws NoSuchFastFoodMarketException, NoSuchFastFoodException, FastFoodMarketHasNotFastFoodException {
//        FastFoodMarket fastFood = fastFoodMarketRepository.findOne(fastfoodmarket_id);//1.5.9
        FastFoodMarket fastFoodMarket = fastFoodMarketRepository.findById(fastfoodmarket_id).get();//2.0.0.M7
        if (fastFoodMarket == null) throw new NoSuchFastFoodMarketException();
//        FastFood fastFood = fastFoodRepository.findOne(fastfood_id);//1.5.9
        FastFood fastFood = fastFoodRepository.findById(fastfood_id).get();//2.0.0.M7
        if (fastFood == null) throw new NoSuchFastFoodException();
        if (fastFood.getFastFoodMarkets().contains(fastFood) == false) throw new FastFoodMarketHasNotFastFoodException();
        fastFood.getFastFoodMarkets().remove(fastFoodMarket);
        fastFoodMarketRepository.save(fastFoodMarket);
    }
}
