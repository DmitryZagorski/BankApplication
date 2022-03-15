package home.intexsoft.bank_application.models;

import home.intexsoft.bank_application.command.Command;
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
@Table(name = "clients")
public class Client extends Model {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_name", columnDefinition = "ClientStatusType('INDIVIDUAL','ENTITY')")
    private Command.ClientStatusType status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
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
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}