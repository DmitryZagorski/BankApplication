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
    private Date createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

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