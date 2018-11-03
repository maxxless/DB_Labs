package com.dbspring.Repository;

import com.dbspring.domain.FastFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFoodRepository extends JpaRepository<FastFood, Long> {
}
