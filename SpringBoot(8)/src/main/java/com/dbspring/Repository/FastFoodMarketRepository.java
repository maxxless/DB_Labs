package com.dbspring.Repository;

import com.dbspring.domain.FastFoodMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFoodMarketRepository extends JpaRepository<FastFoodMarket, Long> {
}
