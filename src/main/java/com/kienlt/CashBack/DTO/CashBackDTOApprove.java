package com.kienlt.CashBack.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashBackDTOApprove {
    public Double myCashBack;
    public Date dateOfApprove;
}
