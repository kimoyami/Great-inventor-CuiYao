package srv;

import java.io.Serializable;
import java.util.Date;

public class ShopRecord implements Serializable {
    private static final long serialVersionUID= 1257524929679219531L;

    private String EcardNumber;
    private String GoodsID;
    private int purchaseAmount;
    private Date purchaseTime;

    public ShopRecord(String EcardNumber,String GoodsID,int purchaseAmount,Date purchaseTime) {
        this.EcardNumber=EcardNumber;
        this.GoodsID=GoodsID;
        this.purchaseAmount=purchaseAmount;
        this.purchaseTime=purchaseTime;
    }


    public String getEcardNumber() {
        return this.EcardNumber;
    }

    public String getGoodsID() {
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

    public void setGoodsID(String GoodsID) {
        this.GoodsID = GoodsID;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

}
