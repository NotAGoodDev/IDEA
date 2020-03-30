package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelCard {

    @JsonProperty("cardNumber")
    private Integer cardNumber;

    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("validateNumber")
    private Integer validateNumber;

    @JsonProperty("expirationDate")
    private String expirationDate;

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getValidateNumber() {
        return validateNumber;
    }

    public void setValidateNumber(Integer validateNumber) {
        this.validateNumber = validateNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
