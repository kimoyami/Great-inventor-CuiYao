package srv;

public class ShopBasket {
    private static final long serialVersionUID= 3313447742974944632L;
    private int goodID;
    private int goodNumber;

    public ShopBasket(int goodID,int goodNumber){
        this.goodID=goodID;
        this.goodNumber=goodNumber;
    }

    public int getGoodID() {
        return goodID;
    }
    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }
    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }
}
