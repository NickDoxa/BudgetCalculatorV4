package net.oasisgames.budgetcalculatorv4.repository;

import net.oasisgames.budgetcalculatorv4.components.BudgetUser;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Budget User Repository class for the Budget Calculator App
 */
@Repository
@Scope("singleton")
public interface UserRepository extends JpaRepository<BudgetUser, String> {

    @Query("select username from BudgetUser")
    List<String> findAllUsernames();

}