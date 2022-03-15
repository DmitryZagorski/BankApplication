package home.intexsoft.bank_application.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccount extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;
    @Column(name = "amount_of_money")
    private Double amountOfMoney;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER)
    private List<Action> actions = new ArrayList<>();
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", currency=" + currency +
                ", amountOfMoney=" + amountOfMoney +
                ", client=" + client +
                '}';
    }
}