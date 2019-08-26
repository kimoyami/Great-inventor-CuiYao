package srv.goods;

public class Goods {
    private static final long serialVersionUID= 5006441955722175923L;
    private String ID;
    private int number;
    private double price;
    private String name;
    private String picturePath;
    private int tag;//0=tianping,1=zhongchao


    public Goods(String ID,int number,String name,double price, String picturePath,int tag) {
            this.tag=tag;
            this.ID=ID;
            this.name=name;
            this.number=number;
            this.picturePath=picturePath;
            this.price=price;
    }

    public String getID() {
        return this.ID;
    }
    public int getNumber() {
        return this.number;
    }
    public double getPrice() { return this.price; }
    public String getName() {
        return this.name;
    }
    public String getPicturePath() {
        return this.picturePath;
    }
    public int getTag() {
        return this.tag;
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
    public void setTag(int tag){this.tag=tag;};

}
