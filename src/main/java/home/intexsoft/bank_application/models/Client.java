package home.intexsoft.bank_application.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column
    private Integer statusId;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private ClientStatus clientStatus;
    @ManyToMany
    @JoinTable(name = "bank_clients",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id"))
    private List<Bank> banks = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<BankAccount> bankAccounts = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<Transaction> transactions = new ArrayList<>();

    public Client() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }


    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }
}
