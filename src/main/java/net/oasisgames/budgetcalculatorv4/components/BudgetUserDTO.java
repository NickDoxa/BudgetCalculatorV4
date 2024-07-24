package net.oasisgames.budgetcalculatorv4.components;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class BudgetUserDTO {

    private String username;
    private int age;
    private double salary;

}
