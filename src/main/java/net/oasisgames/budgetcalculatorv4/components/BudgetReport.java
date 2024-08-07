package net.oasisgames.budgetcalculatorv4.components;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("prototype")
@Entity
@Data
@Table(name = "budget_report")
public class BudgetReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String report;
    private Date date_created;

}
