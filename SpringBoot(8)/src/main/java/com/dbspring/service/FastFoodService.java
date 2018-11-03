package com.dbspring.service;

import com.dbspring.Repository.FastFoodRepository;
import com.dbspring.Repository.FastFoodMarketRepository;
import com.dbspring.domain.FastFood;
import com.dbspring.domain.FastFoodMarket;
import com.dbspring.exceptions.ExistsFastFoodMarketForFastFoodException;
import com.dbspring.exceptions.NoSuchFastFoodException;
import com.dbspring.exceptions.NoSuchFastFoodMarketException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class FastFoodService {
    @Autowired
    FastFoodRepository fastFoodRepository;

    @Autowired
    FastFoodMarketRepository fastFoodMarketRepository;

    public Set<FastFood> getFastFoodsByFastFoodMarketId(Long fastfoodmarket_id) throws NoSuchFastFoodMarketException {
//        FastFoodMarket fastFoodMarket = fastFoodMarketRepository.findOne(fastfoodmarket_id);//1.5.9
        FastFoodMarket fastFoodMarket = fastFoodMarketRepository.findById(fastfoodmarket_id).get();//2.0.0.M7
        if (fastFoodMarket == null) throw new NoSuchFastFoodMarketException();
        return fastFoodMarket.getFastFoodMarkets();
    }

    public FastFood getFastFood(Long fastfood_id) throws NoSuchFastFoodException {
//        FastFood fastfood = fastFoodRepository.findOne(fastfood_id);//1.5.9
        FastFood fastfood = fastFoodRepository.findById(fastfood_id).get();//2.0.0.M7
        if (fastfood == null) throw new NoSuchFastFoodException();
        return fastfood;
    }

    public List<FastFood> getAllFastFoods() {
        return fastFoodRepository.findAll();
    }

    @Transactional
    public void createFastFood(FastFood fastfood) {
        fastFoodRepository.save(fastfood);
    }

    @Transactional
    public void updateFastFood(FastFood uFastFood, Long fastfood_id) throws NoSuchFastFoodException {
//        FastFood fastfood = fastFoodRepository.findOne(fastfood_id);//1.5.9
        FastFood fastfood = fastFoodRepository.findById(fastfood_id).get();//2.0.0.M7
        if (fastfood == null) throw new NoSuchFastFoodException();
        //update
        fastfood.setFastFoodName(uFastFood.getFastFoodName());
        fastfood.setAuthor(uFastFood.getAuthor());
        fastfood.setSeller(uFastFood.getSeller());
        fastfood.setYearOfCreating(uFastFood.getYearOfCreating());
        fastfood.setAmount(uFastFood.getAmount());
    }

    @Transactional
    public void deleteFastFood(Long fastfood_id) throws NoSuchFastFoodException, ExistsFastFoodMarketForFastFoodException {
//        FastFood fastfood = fastFoodRepository.findOne(fastfood_id);//1.5.9
        FastFood fastfood = fastFoodRepository.findById(fastfood_id).get();//2.0.0.M7

        if (fastfood == null) throw new NoSuchFastFoodException();
        if (fastfood.getFastFoodMarkets().size() != 0) throw new ExistsFastFoodMarketForFastFoodException();
        fastFoodRepository.delete(fastfood);
    }
}
