package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentModel {

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("cardNumber")
    private Integer cardNumber;

    @JsonProperty("expirationDate")
    private String expirationDate;

    @JsonProperty("validateNumber")
    private Integer validateNumber;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getValidateNumber() {
        return validateNumber;
    }

    public void setValidateNumber(Integer validateNumber) {
        this.validateNumber = validateNumber;
    }
}
