package net.oasisgames.budgetcalculatorv4.components;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Data
public class BudgetUser {

    @Id
    private String username;
    private int age;
    private double salary;

}
