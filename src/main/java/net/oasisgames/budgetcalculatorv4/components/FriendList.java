package net.oasisgames.budgetcalculatorv4.components;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Entity
@Data
@Table(name = "friend_list")
public class FriendList {

    @Id
    private String username;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BudgetUser> friends;

}
