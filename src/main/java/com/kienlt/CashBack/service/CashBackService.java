package com.kienlt.CashBack.service;

import com.kienlt.CashBack.DTO.CashBackDTOApprove;
import com.kienlt.CashBack.DTO.CashBackDTOSale;

import java.util.List;

public interface CashBackService {
    List<CashBackDTOApprove> GetCashBackApprove(String startDate, String endDate);

    List<CashBackDTOSale> GetCashBackSale(String startDate, String endDate);
}
