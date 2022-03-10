package home.intexsoft.bank_application.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "banks")
public class Bank{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_name")
    private String name;
    @Column(name = "commission_for_individual")
    private Double commissionForIndividual;
    @Column(name = "commission_for_entity")
    private Double commissionForEntity;
    @ManyToMany
    @JoinTable(name = "bank_clients",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clients = new ArrayList<>();
    @OneToMany(mappedBy = "bank")
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public Bank() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id == bank.id &&
                Objects.equals(name, bank.name) &&
                Objects.equals(commissionForIndividual, bank.commissionForIndividual) &&
                Objects.equals(commissionForEntity, bank.commissionForEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, commissionForIndividual, commissionForEntity);
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
