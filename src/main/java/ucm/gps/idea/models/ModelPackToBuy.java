package ucm.gps.idea.models;


public class ModelPackToBuy {

    private Integer numIdeasToBuy;

    private Integer discount;

    public ModelPackToBuy(Integer numIdeasToBuy, Integer discount) {
        this.numIdeasToBuy = numIdeasToBuy;
        this.discount = discount;
    }

    public Integer getNumIdeasToBuy() {
        return numIdeasToBuy;
    }

    public void setNumIdeasToBuy(Integer numIdeasToBuy) {
        this.numIdeasToBuy = numIdeasToBuy;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }



    @Override
    public String toString() {
        return "ModelPackToBuy{" +
                "numIdeasToBuy=" + numIdeasToBuy +
                ", discount=" + discount +
                '}';
    }
}
