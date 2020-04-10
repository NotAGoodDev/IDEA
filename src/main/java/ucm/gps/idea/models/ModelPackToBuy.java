package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelPackToBuy {

    @JsonProperty("cardNumber")
    private Integer cardNumber;

    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("validateNumber")
    private Integer validateNumber;

    @JsonProperty("expirationDate")
    private String expirationDate;

    @JsonProperty("numIdeasToBuy")
    private Integer numIdeasToBuy;

    @JsonProperty("discount")
    private Integer discount;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getNumIdeasToBuy() {
        return numIdeasToBuy;
    }

    public void setNumIdeasToBuy(Integer numIdeasToBuy) {
        this.numIdeasToBuy = numIdeasToBuy;
    }

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

    @Override
    public String toString() {
        return "ModelPackToBuy{" +
                "cardNumber=" + cardNumber +
                ", ownerName='" + ownerName + '\'' +
                ", validateNumber=" + validateNumber +
                ", expirationDate='" + expirationDate + '\'' +
                ", numIdeasToBuy=" + numIdeasToBuy +
                ", discount=" + discount +
                '}';
    }
}
