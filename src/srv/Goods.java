package srv;

public class Goods {
    private static final long serialVersionUID= 5006441955722175923L;
    private String ID;
    private int number;
    private double priceInZhongchao;
    private double priceInTianPing;

    private String name;
    private String picturePath;

    public Goods() {
        setID(null);
        setName(null);
        setNumber(1);
        setPicturePath(null);
        setPriceInTianPing(0);
        setPriceInZhongchao(0);
    }

    public String getID() {
        return this.ID;
    }
    public int getNumber() {
        return this.number;
    }
    public double getPriceInZhongchao() { return this.priceInZhongchao; }
    public double getPriceInTianPing() { return this.priceInTianPing; }
    public String getName() {
        return this.name;
    }
    public String getPicturePath() {
        return this.picturePath;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setPriceInZhongchao(double price) {
        this.priceInZhongchao = price;
    }
    public void setPriceInTianPing(double price) {
        this.priceInTianPing = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    public double ComparePrice(){
        return  priceInZhongchao-priceInTianPing;
    }
}
