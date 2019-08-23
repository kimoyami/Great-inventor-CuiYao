package srv;

public class ShopBasket {
    private static final long serialVersionUID= 3313447742974944632L;
    private int goodID;
    private int goodNumber;

    public int getGoodID() {
        return goodID;
    }
    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int _goodNumber) {
        this.goodNumber = _goodNumber;
    }
    public void setGoodID(int _goodID) {
        this.goodID = goodID;
    }
}
