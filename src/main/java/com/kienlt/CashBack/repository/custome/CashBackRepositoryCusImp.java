package com.kienlt.CashBack.repository.custome;

import com.kienlt.CashBack.DTO.CashBackDTOApprove;
import com.kienlt.CashBack.DTO.CashBackDTOSale;
import com.kienlt.CashBack.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CashBackRepositoryCusImp implements CashBackRepositoryCus {

    @Autowired
    @Qualifier("EntityManager")
    EntityManager entityManager;

    @Override
    public List<CashBackDTOApprove> getCashBackByDateOfApprove(String startDate,String endDate) {
        Query query = entityManager.createNativeQuery("select sum(mycashback) as myCashBack,trunc(DATEOFAPPROVE) as dateOfApprove from CASHBACK\n" +
                "where trunc(DATEOFAPPROVE) between to_date(:startDate,'dd/mm/yyyy') and to_date(:endDate,'dd/mm/yyyy')\n" +
                "group by trunc(DATEOFAPPROVE) order by trunc(DATEOFAPPROVE) desc", Constants.RESULT_SET_MAPPING.CASHBACK_DTO_APPROVE);
        query.setParameter("startDate",startDate);
        query.setParameter("endDate",endDate);

        List<CashBackDTOApprove> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<CashBackDTOSale> getCashBackByDateOfSale(String startDate,String endDate) {
        Query query = entityManager.createNativeQuery("select sum(mycashback) as myCashBack,trunc(DATEOFSALE) as dateOfSale from CASHBACK\n" +
                "where trunc(DATEOFSALE) between to_date(:startDate,'dd/mm/yyyy') and to_date(:endDate,'dd/mm/yyyy')\n" +
                "group by trunc(DATEOFSALE)  order by trunc(DATEOFSALE) desc", Constants.RESULT_SET_MAPPING.CASHBACK_DTO_SALE);
        query.setParameter("startDate",startDate);
        query.setParameter("endDate",endDate);

        List<CashBackDTOSale> ret = query.getResultList();

        return ret;
    }
}
