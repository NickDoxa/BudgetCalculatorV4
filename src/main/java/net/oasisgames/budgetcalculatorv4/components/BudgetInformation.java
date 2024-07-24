package net.oasisgames.budgetcalculatorv4.components;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Data
public class BudgetInformation {

    @Id
    private String username;
    private Long report_id;
    private double remainder;
    private double expenses;
    private double taxes;

}