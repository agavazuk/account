
package com.ag.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Transaction {


    private java.lang.Long date;

    private java.math.BigDecimal amount;

    private Boolean credit;

    private String uuid;

    @JsonIgnore
    private Account account;


    public Transaction() {
    }


    @JsonProperty("date")
    public java.lang.Long getDate() {
        return date;
    }

    public void setDate(java.lang.Long date) {
        this.date = date;
    }


    @JsonProperty("amount")
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }


    @JsonProperty("credit")
    public Boolean getCredit() {
        return credit;
    }

    public void setCredit(Boolean credit) {
        this.credit = credit;
    }


    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @JsonProperty("account")
    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction Transaction = (Transaction) o;

        return Objects.equals(date, Transaction.date) &&
                Objects.equals(amount, Transaction.amount) &&
                Objects.equals(credit, Transaction.credit) &&
                Objects.equals(uuid, Transaction.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, credit, uuid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Transaction {\n");

        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    credit: ").append(toIndentedString(credit)).append("\n");
        sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
        sb.append("    account: ").append(toIndentedString(account)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
