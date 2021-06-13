package com.kienlt.CashBack.controller;

import java.util.List;


import com.kienlt.CashBack.DTO.CashBackDTOApprove;
import com.kienlt.CashBack.DTO.CashBackDTOSale;
import com.kienlt.CashBack.business.GetCashBack;
import com.kienlt.CashBack.entity.CashBack;
import com.kienlt.CashBack.entity.ContentHTML;
import com.kienlt.CashBack.repository.ICashBackRepository;
import com.kienlt.CashBack.service.CashBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CashBackController {
    @Autowired
    ICashBackRepository cashBackRepository;

    @Autowired
    CashBackService cashBackService;

    @PostMapping(value = "/getCashBack")
    public ResponseEntity<?> getCashBack(@RequestBody ContentHTML content) {

        GetCashBack cback = new GetCashBack(cashBackRepository);
        List<CashBack> lstCashBack = null;
        try {
            Integer rs = cback.formatHTML(content.getContent());
            if (rs == 1)
                lstCashBack = cback.HandleHTML();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(lstCashBack, HttpStatus.OK);
    }

    @GetMapping(value = "/getCashBackAprrove")
    public ResponseEntity<?> getCashBackAprrove(@RequestParam(required = false) String startDate,
                                                @RequestParam(required = false) String endDate) {
        List<CashBackDTOApprove> lstCashBackAprrove = null;
        try {
            lstCashBackAprrove = cashBackService.GetCashBackApprove(startDate, endDate);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(lstCashBackAprrove, HttpStatus.OK);
    }

    @GetMapping(value = "/getCashBackSale")
    public ResponseEntity<?> getCashBackSale(@RequestParam(required = false) String startDate,
                                             @RequestParam(required = false) String endDate) {
        List<CashBackDTOSale> lstCashBackSale = null;
        try {
            lstCashBackSale = cashBackService.GetCashBackSale(startDate, endDate);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(lstCashBackSale, HttpStatus.OK);
    }
}
