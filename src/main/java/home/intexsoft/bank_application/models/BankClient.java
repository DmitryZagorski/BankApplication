package home.intexsoft.bank_application.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_clients")
public class BankClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_id")
    private Integer bankId;
    @Column(name = "client_id")
    private Integer clientId;
    private String bankName;
    private String clientName;
    private String clientSurname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        BankClient that = (BankClient) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bankId, that.bankId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankId, clientId);
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", clientId=" + clientId +
                ", bankName='" + bankName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                '}';
    }
}
