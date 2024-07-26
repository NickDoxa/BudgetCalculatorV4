package net.oasisgames.budgetcalculatorv4.services;

import lombok.Getter;
import net.oasisgames.budgetcalculatorv4.BudgetCalculatorV4Application;
import net.oasisgames.budgetcalculatorv4.components.*;
import net.oasisgames.budgetcalculatorv4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service class for the Budget App logic
 */
@Getter
@Service
public class BudgetService {

    @Autowired
    public BudgetService(InformationRepository infoRepository,
                         UserRepository userRepository,
                         ReportRepository reportRepository,
                         FriendRepository friendRepository,
                         Calculate calculate, BudgetMapper mapper) {
        this.infoRepository = infoRepository;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.friendRepository = friendRepository;
        this.calculate = calculate;
        this.mapper = mapper;
    }

    private final InformationRepository infoRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final FriendRepository friendRepository;
    private final Calculate calculate;
    private final BudgetMapper mapper;

    /**
     * Calculate a budget report for the user
     * and save their information using the repository
     * @param input The Budget Input Data Transfer Object (DTO)
     *              filled with data to use in the report
     * @return Budget Report as a String
     */
    public BudgetReportDTO calculateBudgetReportAndSave(BudgetInputDTO input) {
        double income = getUserRepository().findSalaryByUsername(input.getUsername());
        double remainder = calculate.calculateTotalRemainder(input.getMonthlyExpenses(),
                input.getWeeklyExpenses(), income);
        double tax_percent = calculate.calculateFederalTax(income);
        double taxes_taken = income * tax_percent;
        double expenses = calculate.calculateTotalExpenses(input.getMonthlyExpenses(),
                input.getWeeklyExpenses());
        BudgetInformation info = mapper.inputDTOToInfo(input);
        info.setRemainder(remainder);
        info.setTaxes(taxes_taken);
        info.setExpenses(expenses);
        String final_report = "Your gross income was " + Calculate.formatToCurrency(income) +
                ". After losing " + Calculate.formatToCurrency(taxes_taken) +
                " to taxes, your income becomes " +
                Calculate.formatToCurrency(income - taxes_taken) +
                ". Your expenses add up to " + Calculate.formatToCurrency(expenses) +
                ". This leaves you with a final remainder of " +
                Calculate.formatToCurrency(remainder);
        BudgetReport report = createBudgetReport(input.getUsername(), final_report);
        info.setReport(report);
        getInfoRepository().save(info);
        return mapper.reportToDTO(report);
    }

    /**
     * Creates the Budget Report Object
     * @param username username of the budget owner
     * @param string_report budget report as a string
     * @return Budget Report Object
     */
    private BudgetReport createBudgetReport(String username,
                                            String string_report) {
        BudgetReport report = BudgetCalculatorV4Application.getContext()
                .getBean(BudgetReport.class);
        report.setUsername(username);
        report.setReport(string_report);
        report.setDate_created(new Date());
        return report;
    }

    /**
     * Gets all the previous budget reports from one user
     * @param user the user to retrieve report data from
     * @return List of Budget Report Data Transfer Objects
     */
    public List<BudgetReportDTO> getAllUserBudgetReports(String user) {
        return reportRepository.findBudgetReportsByUsername(user)
                .stream()
                .map(mapper::reportToDTO)
                .toList();
    }

    /**
     * Creates a new user and returns the Budget User DTO
     * @param userDTO the users data as a Data Transfer Object (DTO)
     * @return Budget User Data Transfer Object (DTO) with new values
     */
    public BudgetUserDTO createNewUser(BudgetUserDTO userDTO) {
        BudgetUser user = mapper.dtoToUser(userDTO);
        getUserRepository().save(user);
        return mapper.userToDTO(getUserRepository()
                .findById(userDTO.getUsername()).orElse(null));
    }

    /**
     * Gets a Budget Information DTO from the users name
     * @param user the user to retrieve data from
     * @return Budget Information Data Transfer Object (DTO)
     */
    public BudgetInformationDTO getBudgetInformation(String user) {
        return mapper.infoToDTO(infoRepository.findById(user).orElse(null));
    }

    /**
     * Gets a Budget User DTO from the users name
     * @param user the user to retrieve data from
     * @return Budget User Data Transfer Object (DTO)
     */
    public BudgetUserDTO getBudgetUser(String user) {
        return mapper.userToDTO(userRepository.findById(user).orElse(null));
    }

    /**
     * Gets the last Budget Report DTO from the users records
     * @param user the user to retrieve report data from
     * @return Budget Report Data Transfer Object (DTO)
     */
    public BudgetReportDTO getLastBudgetReport(String user) {
        return mapper.reportToDTO(getReportRepository()
                .findUsersLastReport(user).orElse(null));
    }

    /**
     * Adds the specific friend to the users friend list
     * @param user the user adding a friend
     * @param friend_username the user to add to the friend list
     * @return Friend List Data Transfer Object (DTO) with updated list of friends
     */
    public FriendListDTO addFriend(String user, String friend_username) {
        BudgetUser budgetUser = getUserRepository().findById(user).orElseThrow();
        FriendList friends = getFriendRepository().findById(user).orElse(
                BudgetCalculatorV4Application.getContext().getBean(FriendList.class));
        List<BudgetUser> list = friends.getFriends() == null ?
                new ArrayList<>() :
                friends.getFriends();
        list.add(getUserRepository().findById(friend_username).orElse(null));
        friends.setFriends(list);
        friends.setUsername(user);
        getFriendRepository().save(friends);
        return mapper.friendToDTO(getFriendRepository()
                .findById(budgetUser.getUsername()).orElseThrow());
    }

    /**
     * Gets all of a users friends
     * @param user the user to get the friend list from
     * @return Friend List Data Transfer Object (DTO) of the users friends
     */
    public FriendListDTO getFriends(BudgetUserDTO user) {
        return mapper.friendToDTO(getFriendRepository()
                .findById(user.getUsername()).orElseThrow());
    }

}
