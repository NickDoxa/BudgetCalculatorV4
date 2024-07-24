package net.oasisgames.budgetcalculatorv4.repository;

import net.oasisgames.budgetcalculatorv4.components.BudgetReport;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Budget Report Repository class for the Budget Calculator App
 */
@Repository
@Scope("singleton")
public interface ReportRepository extends JpaRepository<BudgetReport, Long> {

    @Query("select br from BudgetReport br where br.username=?1")
    List<BudgetReport> findBudgetReportsByUsername(String user);
    @Query("select report from BudgetReport report " +
            "inner join BudgetInformation info " +
            "on info.report_id = report.id " +
            "where info.username=?1")
    Optional<BudgetReport> findUsersLastReport(String user);

}
