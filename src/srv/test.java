package srv;

import java.util.ArrayList;

public class test {
    public static void main(String args[]) {
        ArrayList<GoodsOfTianping> list = new ArrayList<>();
        GoodsOfTianping good1 = new GoodsOfTianping();
        GoodsOfTianping good2 = new GoodsOfTianping();
        GoodsOfTianping good3 = new GoodsOfTianping();
        good1.setPrice(10);
        good2.setPrice(20);
        good3.setPrice(30);
        list.add(good1);
        list.add(good2);
        list.add(good3);
        System.out.println(good1.calTotcalPrice(list));
    }
}
