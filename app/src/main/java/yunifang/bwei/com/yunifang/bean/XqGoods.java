package yunifang.bwei.com.yunifang.bean;

import java.util.List;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/28.
 * 作用：
 */
public class XqGoods {
    public List<XqImg> gallery;
    public String goods_name;
    public String market_price;
    public String shop_price;

    public XqGoods(String goods_name, String market_price, String shop_price) {
        this.goods_name = goods_name;
        this.market_price = market_price;
        this.shop_price = shop_price;
    }
}
