package srv;

import java.io.Serializable;

public class GoodsOfZhongchao implements Serializable {
    private static final long serialVersionUID= 5006441955722175923L;
    private String ID;
    private int number;
    private double price;
    private String name;
    private String picturePath;

    public GoodsOfZhongchao() {
        setID(null);
        setName(null);
        setNumber(1);
        setPicturePath(null);
        setPrice(0);
    }

    public String getID() {
        return this.ID;
    }
    public int getNumber() {
        return this.number;
    }
    public double getPrice() {
        return this.price;
    }
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
    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

}
