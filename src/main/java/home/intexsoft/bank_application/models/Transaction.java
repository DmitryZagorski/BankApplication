package home.intexsoft.bank_application.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    private Integer clientId;
    //    @ManyToOne
//    @JoinColumn(name = "sender_bank_account_id", referencedColumnName = "id")
    private Integer senderBankAccountId;
    //    @ManyToOne
//    @JoinColumn(name = "recipient_bank_account_id", referencedColumnName = "id")
    private Integer recipientBankAccountId;
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;
    private Integer currencyId;
    @Column(name = "amount_of_money")
    private Double amountOfMoney;
    @Column(name = "creation_date")
    private Date creationDate;
    private String clientName;
    private String clientSurname;
    private String currencyName;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSenderBankAccountId() {
        return senderBankAccountId;
    }

    public void setSenderBankAccountId(Integer senderBankAccountId) {
        this.senderBankAccountId = senderBankAccountId;
    }

    public Integer getRecipientBankAccountId() {
        return recipientBankAccountId;
    }

    public void setRecipientBankAccountId(Integer recipientBankAccountId) {
        this.recipientBankAccountId = recipientBankAccountId;
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
