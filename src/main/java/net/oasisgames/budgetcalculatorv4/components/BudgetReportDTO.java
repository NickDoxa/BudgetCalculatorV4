package net.oasisgames.budgetcalculatorv4.components;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("prototype")
@Data
public class BudgetReportDTO {

    private long id;
    private String username;
    private String report;
    private Date date_created;

}
