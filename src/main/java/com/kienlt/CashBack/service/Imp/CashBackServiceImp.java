package com.kienlt.CashBack.service.Imp;


import com.kienlt.CashBack.DTO.CashBackDTOApprove;
import com.kienlt.CashBack.DTO.CashBackDTOSale;
import com.kienlt.CashBack.repository.custome.CashBackRepositoryCus;
import com.kienlt.CashBack.service.CashBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashBackServiceImp implements CashBackService {
    @Autowired
    CashBackRepositoryCus cashBackRepositoryCus;

    @Override
    public List<CashBackDTOApprove> GetCashBackApprove(String startDate, String endDate) {
        List<CashBackDTOApprove> lstCashBackDTOApprove = cashBackRepositoryCus.getCashBackByDateOfApprove(startDate, endDate);
        return lstCashBackDTOApprove;
    }

    @Override
    public List<CashBackDTOSale> GetCashBackSale(String startDate, String endDate) {
        List<CashBackDTOSale> lstCashBackDTOSale = cashBackRepositoryCus.getCashBackByDateOfSale(startDate, endDate);
        return lstCashBackDTOSale;
    }
}
