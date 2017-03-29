package yunifang.bwei.com.yunifang.bean;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/24.
 * 作用：
 */
public class GoodsList {
    public String goods_img;

    public GoodsList(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_img() {
        return goods_img;
    }

    @Override
    public String toString() {
        return "GoodsList{" +
                "goods_img='" + goods_img + '\'' +
                '}';
    }
}
