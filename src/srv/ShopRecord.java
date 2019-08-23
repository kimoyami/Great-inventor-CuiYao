package srv;

import java.io.Serializable;
import java.util.Date;

public class ShopRecord implements Serializable {
    private static final long serialVersionUID= 1257524929679219531L;

    private String EcardNumber;
    private int GoodsID;
    private int purchaseAmount;
    private Date purchaseTime;

    public ShopRecord() {
        setEcardNumber(null);
        setGoodsID(0);
        setPurchaseAmount(0);
        setPurchaseTime(new Date());
    }


    public String getEcardNumber() {
        return this.EcardNumber;
    }

    public int getGoodsID() {
        return this.GoodsID;
    }

    public int getPurchaseAmount() {
        return this.purchaseAmount;
    }

    public Date getPurchaseTime() {
        return this.purchaseTime;
    }

    public void setEcardNumber(String EcardNumber) {
        this.EcardNumber = EcardNumber;
    }

    public void setGoodsID(int GoodsID) {
        this.GoodsID = GoodsID;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

}
