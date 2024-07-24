package net.oasisgames.budgetcalculatorv4;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BudgetCalculatorV4Application {

    @Getter
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(BudgetCalculatorV4Application.class, args);
    }

}
