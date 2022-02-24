package home.intexsoft.bank_application.models;

import java.util.Objects;

public class Bank {

    private int id;
    private String name;
    private Double commissionForIndividual;
    private Double commissionForEntity;

    public Bank() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, bank.id) &&
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
