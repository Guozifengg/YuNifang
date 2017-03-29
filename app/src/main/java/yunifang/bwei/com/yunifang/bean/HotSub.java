package yunifang.bwei.com.yunifang.bean;

import java.util.List;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/23.
 * 作用：
 */
public class HotSub {
    public String image;
    public List<GoodsList> goodsList;

    public HotSub(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "HotSub{" +
                "image='" + image + '\'' +
                '}';
    }
}
