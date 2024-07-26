package net.oasisgames.budgetcalculatorv4.repository;

import net.oasisgames.budgetcalculatorv4.components.FriendList;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The FriendList repository class for the BudgetCalculator App
 */
@Repository
@Scope("singleton")
public interface FriendRepository extends JpaRepository<FriendList, String> {}
