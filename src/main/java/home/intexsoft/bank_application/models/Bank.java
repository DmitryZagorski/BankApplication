package home.intexsoft.bank_application.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCommissionForIndividual() {
        return commissionForIndividual;
    }

    public void setCommissionForIndividual(Double commissionForIndividual) {
        this.commissionForIndividual = commissionForIndividual;
    }

    public Double getCommissionForEntity() {
        return commissionForEntity;
    }

    public void setCommissionForEntity(Double commissionForEntity) {
        this.commissionForEntity = commissionForEntity;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }



    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commissionForIndividual=" + commissionForIndividual +
                ", commissionForEntity=" + commissionForEntity +
                '}';
    }
}
