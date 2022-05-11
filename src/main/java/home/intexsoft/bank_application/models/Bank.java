package home.intexsoft.bank_application.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "banks")
public class Bank extends Model {

    @XmlElement
    @Column(name = "bank_name")
    private String name;
    @XmlElement
    @Column(name = "commission_for_individual")
    private Double commissionForIndividual;
    @XmlElement
    @Column(name = "commission_for_entity")
    private Double commissionForEntity;
    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
    @XmlTransient
    private List<Client> clients = new ArrayList<>();
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @XmlTransient
    private Date createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    @XmlTransient
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