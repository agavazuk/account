
package com.ag.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Account {


    private String customerId;

    private String uuid;

    private java.lang.Long createdDate;


    public Account() {
    }


    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @JsonProperty("createdDate")
    public java.lang.Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.lang.Long createdDate) {
        this.createdDate = createdDate;
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
                Objects.equals(uuid, Account.uuid) &&

                Objects.equals(createdDate, Account.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, uuid, createdDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");

        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
        sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
        sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
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
