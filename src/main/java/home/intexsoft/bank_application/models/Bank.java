package home.intexsoft.bank_application.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "banks")
public class Bank extends Model {

    @Column(name = "bank_name")
    private String name;
    @Column(name = "commission_for_individual")
    private Double commissionForIndividual;
    @Column(name = "commission_for_entity")
    private Double commissionForEntity;
    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
    private List<Client> clients = new ArrayList<>();
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Calendar createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Calendar updateTime;

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommissionForIndividual(Double commissionForIndividual) {
        this.commissionForIndividual = commissionForIndividual;
    }

    public void setCommissionForEntity(Double commissionForEntity) {
        this.commissionForEntity = commissionForEntity;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", commissionForIndividual=" + commissionForIndividual +
                ", commissionForEntity=" + commissionForEntity +
                '}';
    }
}