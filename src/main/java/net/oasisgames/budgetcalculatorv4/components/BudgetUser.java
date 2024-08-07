package net.oasisgames.budgetcalculatorv4.components;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Data
@Table(name = "budget_user")
public class BudgetUser {

    @Id
    private String username;
    private int age;
    private double salary;

}
