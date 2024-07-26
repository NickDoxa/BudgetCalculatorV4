package net.oasisgames.budgetcalculatorv4.repository;

import net.oasisgames.budgetcalculatorv4.components.BudgetInformation;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Budget Information Repository class for the Budget Calculator App
 */
@Repository
@Scope("singleton")
public interface InformationRepository extends JpaRepository<BudgetInformation, String> {}
