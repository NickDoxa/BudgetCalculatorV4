package net.oasisgames.budgetcalculatorv4.controllers;

import net.oasisgames.budgetcalculatorv4.components.BudgetInformationDTO;
import net.oasisgames.budgetcalculatorv4.components.BudgetInputDTO;
import net.oasisgames.budgetcalculatorv4.components.BudgetReportDTO;
import net.oasisgames.budgetcalculatorv4.components.BudgetUserDTO;
import net.oasisgames.budgetcalculatorv4.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for the Budget App
 */
@RestController
public class BudgetController {

    private BudgetService budgetService;

    /**
     * Post Mapping for the budget path
     * @return Budget report in JSON format
     */
    @PostMapping("budget")
    public BudgetReportDTO sendBudgetData(@RequestBody BudgetInputDTO budgetInputDTO) {
        return budgetService.calculateBudgetReportAndSave(budgetInputDTO);
    }

    /**
     * Get Mapping for the my_budget path
     * @param user the users name
     * @return Budget information in JSON format
    */
    @GetMapping("budget")
    public BudgetInformationDTO getBudgetData(String user) {
        return budgetService.getBudgetInformation(user);
    }

    /**
     * Get Mapping for the users path
     * @return Users list in JSON format
     */
    @GetMapping("users")
    public List<String> getAllUsers() {
        return budgetService.getUserRepository().findAllUsernames();
    }

    /**
     * Get mapping for the users path with a user path variable
     * @param user the user to retrieve
     * @return Budget User Data Transfer Object (DTO)
     */
    @GetMapping("users/{user}")
    public BudgetUserDTO getUser(@PathVariable String user) {
        return budgetService.getBudgetUser(user);
    }

    /**
     * Get mapping for all the users past reports
     * @param user the user to retrieve reports from
     * @return List of the users past reports as a
     * Budget Report Data Transfer Object (DTO)
     */
    @GetMapping("past_reports")
    public List<BudgetReportDTO> getAllPreviousReports(String user) {
        return budgetService.getAllUserBudgetReports(user);
    }

    /**
     * Get mapping for the users last generated budget report in a DTO
     * @param user the user to find the report from
     * @return The Budget Report Data Transfer Object (DTO)
     */
    @GetMapping("last_report")
    public BudgetReportDTO getLastReport(String user) {
        return budgetService.getLastBudgetReport(user);
    }

    /**
     * Post mapping to create a new user in the user database and get the results as a DTO
     * @param budgetUserDTO the user dto to transfer
     * @return Completed Budget User Data Transfer Object (DTO)
     */
    @PostMapping("new_user")
    public BudgetUserDTO createNewUser(@RequestBody BudgetUserDTO budgetUserDTO) {
        return budgetService.createNewUser(budgetUserDTO);
    }

    /**
     * Autowired setter for the BudgetService object
     * @param budgetService the budget service object (Autowired)
     */
    @Autowired
    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

}
