package home.intexsoft.bank_application.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "currency")
public class Currency extends Model {

    @Column(name = "currency_name")
    private String name;
    @Column(name = "rate")
    private Double rate;
    @OneToMany(mappedBy = "currency", fetch = FetchType.EAGER)
    private List<BankAccount> bankAccounts = new ArrayList<>();
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
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}