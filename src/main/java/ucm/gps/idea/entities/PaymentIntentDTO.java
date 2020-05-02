package ucm.gps.idea.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentIntentDTO {

    public enum Currency{
        USD, EUR;
    }

    private String description;
    private Integer amount;
    private Currency currency;
    private String ownerName;
    private String cardNumber;
    private Date expirationDate;
    private String validateNumber;

    public PaymentIntentDTO(String desc, Integer amt, String curr, String owner, String card, String exDate, String cvv){
        description = desc;
        amount = amt;
        currency = curr.equalsIgnoreCase("EUR") ? Currency.EUR : Currency.USD;
        ownerName = owner;
        cardNumber = card;
        expirationDate = stringToDate(exDate);
        validateNumber = cvv;
    }
    public PaymentIntentDTO(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getValidateNumber() {
        return validateNumber;
    }

    public void setValidateNumber(String validateNumber) {
        this.validateNumber = validateNumber;
    }

    private Date stringToDate(String stringDate){
        try{
            return new SimpleDateFormat("yy-mm-dd").parse(stringDate);
        }catch(Exception e){
            return null;
        }
    }
}
