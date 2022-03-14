package home.intexsoft.bank_application.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "operations")
public class Operation extends Model {

    @Column(name = "status")
    private String status;
    @Column(name = "name")
    private String name;  // type
    @OneToMany(mappedBy = "operation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Calendar createTime;       // date
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Calendar updateTime;

    public Operation() {
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Action> getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}