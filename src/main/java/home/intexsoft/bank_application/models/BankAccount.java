package home.intexsoft.bank_application.models;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer currencyId;
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;
    @Column(name = "amount_of_money")
    private Double amountOfMoney;
    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;
    private Integer bankId;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    private Integer clientId;
    private String currencyName;
    private String bankName;
    private String clientName;
    private String clientSurname;
//    @OneToMany(mappedBy = "sender_bank_account_id")
//    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount() {

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<Transaction> transactions) {
//        this.transactions = transactions;
//    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
