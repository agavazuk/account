
package com.ag.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Account {


    private String customerId;

    private java.lang.Long createdDate;

    private java.math.BigDecimal balance;

    private java.util.List<Transaction> transactions;
    
    private String uuid;


    public Account() {
    }


    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    @JsonProperty("createdDate")
    public java.lang.Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.lang.Long createdDate) {
        this.createdDate = createdDate;
    }


    @JsonProperty("balance")
    public java.math.BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }


    @JsonProperty("transactions")
    public java.util.List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(java.util.List<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    
    
    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account Account = (Account) o;

        return Objects.equals(customerId, Account.customerId) &&
                Objects.equals(createdDate, Account.createdDate) &&
                Objects.equals(uuid, Account.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, createdDate, uuid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");

        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
        sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
        sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
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
