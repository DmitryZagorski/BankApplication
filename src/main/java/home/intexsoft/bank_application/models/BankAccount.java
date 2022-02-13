package home.intexsoft.bank_application.models;

import java.util.Objects;

public class BankAccount {

    private int id;
    private Integer currencyId;
    private Double amountOfMoney;
    private Integer bankId;
    private Integer clientId;
    private String currencyName;
    private String bankName;
    private String clientName;
    private String clientSurname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(currencyId, that.currencyId) &&
                Objects.equals(amountOfMoney, that.amountOfMoney) &&
                Objects.equals(bankId, that.bankId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyId, amountOfMoney, bankId, clientId);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", currencyId=" + currencyId +
                ", amountOfMoney=" + amountOfMoney +
                ", bankId=" + bankId +
                ", clientId=" + clientId +
                ", currencyName='" + currencyName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                '}';
    }
}
