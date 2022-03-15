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
@Table(name = "operations")
public class Operation extends Model {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Command.OperationStatus status;
    @Column(name = "name")
    private String type;
    @OneToMany(mappedBy = "operation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        return "Operation{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", name='" + type + '\'' +
                '}';
    }
}