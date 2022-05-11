package home.intexsoft.bank_application.models;

import home.intexsoft.bank_application.command.Command;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "actions")
public class Action extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;
    @Column(name = "amount_of_money")
    private Double amountOfMoney;
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private Command.ActionType actionType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation operation;
    @Column(name = "priority")
    private Integer priority;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

}