package com.kienlt.CashBack.repository.custome;

import com.kienlt.CashBack.DTO.CashBackDTOApprove;
import com.kienlt.CashBack.DTO.CashBackDTOSale;

import java.util.List;

public interface CashBackRepositoryCus {
    List<CashBackDTOApprove> getCashBackByDateOfApprove(String startDate,String endDate);

    List<CashBackDTOSale> getCashBackByDateOfSale(String startDate,String endDate);
}
