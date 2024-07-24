package net.oasisgames.budgetcalculatorv4.repository;

import net.oasisgames.budgetcalculatorv4.components.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    @Mapping(target="BudgetInformation.report", ignore = true)
    BudgetInformationDTO infoToDTO(BudgetInformation budgetInformation);
    @Mapping(target="BudgetInputDTO.weeklyExpenses", ignore = true)
    @Mapping(target="BudgetInputDTO.monthlyExpenses", ignore = true)
    @Mapping(target="BudgetInputDTO.income", ignore = true)
    BudgetInformation inputDTOToInfo(BudgetInputDTO budgetInputDTO);
    BudgetUserDTO userToDTO(BudgetUser budgetUser);
    BudgetUser dtoToUser(BudgetUserDTO budgetUserDTO);
    BudgetReportDTO reportToDTO(BudgetReport budgetReport);

}
