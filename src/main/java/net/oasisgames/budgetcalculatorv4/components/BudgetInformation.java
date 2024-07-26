package net.oasisgames.budgetcalculatorv4.components;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Data
@Table(name = "budget_information")
public class BudgetInformation {

    @Id
    private String username;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private BudgetReport report;
    private double remainder;
    private double expenses;
    private double taxes;

}