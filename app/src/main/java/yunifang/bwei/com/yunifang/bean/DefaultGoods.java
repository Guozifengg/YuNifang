package yunifang.bwei.com.yunifang.bean;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/17.
 * 作用：
 */
public class DefaultGoods {
    public String goods_img;
    public String efficacy;
    public String goods_name;
    public String shop_price;
    public String market_price;
    public String id;

    public DefaultGoods(String goods_img, String efficacy, String goods_name, String shop_price, String market_price) {
        this.goods_img = goods_img;
        this.efficacy = efficacy;
        this.goods_name = goods_name;
        this.shop_price = shop_price;
        this.market_price = market_price;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getShop_price() {
        return shop_price;
    }

    public String getMarket_price() {
        return market_price;
    }

    @Override
    public String toString() {
        return "DefaultGoods{" +
                "goods_img='" + goods_img + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", shop_price='" + shop_price + '\'' +
                ", market_price='" + market_price + '\'' +
                '}';
    }
}
