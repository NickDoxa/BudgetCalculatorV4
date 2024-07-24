package net.oasisgames.budgetcalculatorv4.components;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class BudgetInformationDTO {

    private String username;
    private double remainder;
    private double expenses;
    private double taxes;

}
