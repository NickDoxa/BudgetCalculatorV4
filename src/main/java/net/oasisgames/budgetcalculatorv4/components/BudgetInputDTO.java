package net.oasisgames.budgetcalculatorv4.components;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class BudgetInputDTO {

    private String username;
    private double[] monthlyExpenses;
    private double[] weeklyExpenses;
    private double income;

}
