package net.oasisgames.budgetcalculatorv4.components;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Data
public class FriendListDTO {

    private List<BudgetUserDTO> friends;

}
