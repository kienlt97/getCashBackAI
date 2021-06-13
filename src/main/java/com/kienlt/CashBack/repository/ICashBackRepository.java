package com.kienlt.CashBack.repository;

import com.kienlt.CashBack.entity.CashBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ICashBackRepository extends JpaRepository<CashBack, Long> {
    CashBack findByDateOfSaleAndMyCashBackAndAwaitAndStatus(Date dtOfS, Double myCB, Integer aw, String stt);
}
