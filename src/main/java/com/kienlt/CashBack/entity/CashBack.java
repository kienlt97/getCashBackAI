package com.kienlt.CashBack.entity;

import java.util.Date;

import com.kienlt.CashBack.DTO.CashBackDTOApprove;
import com.kienlt.CashBack.DTO.CashBackDTOSale;
import com.kienlt.CashBack.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SqlResultSetMapping(
        name = Constants.RESULT_SET_MAPPING.CASHBACK_DTO_APPROVE,
        classes = @ConstructorResult(
                targetClass = CashBackDTOApprove.class,
                columns = {
                        @ColumnResult(name = "myCashBack", type = Double.class),
                        @ColumnResult(name = "dateOfApprove", type = Date.class)
                }
        )
)
@SqlResultSetMapping(
        name = Constants.RESULT_SET_MAPPING.CASHBACK_DTO_SALE,
        classes = @ConstructorResult(
                targetClass = CashBackDTOSale.class,
                columns = {
                        @ColumnResult(name = "myCashBack", type = Double.class),
                        @ColumnResult(name = "dateOfSale", type = Date.class)
                }
        )
)

@Entity
@Table(name = "CASHBACK", schema = "MONEY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashBack {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CASHBACK")
    @SequenceGenerator(sequenceName = "SEQ_CASHBACK", allocationSize = 1, name = "SEQ_CASHBACK")
    private Long id;

    @Basic
    @Column(name = "DATEOFSALE")
    private Date dateOfSale;

    @Basic
    @Column(name = "MYCASHBBACK")
    private Double myCashBack;

    @Basic
    @Column(name = "AWAIT")
    private Integer await;

    @Basic
    @Column(name = "STATUS")
    private String status;

    @Basic
    @Column(name = "DATEOFAPPROVE")
    private Date dateOfApprove;
}
