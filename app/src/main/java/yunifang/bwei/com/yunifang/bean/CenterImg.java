package yunifang.bwei.com.yunifang.bean;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/17.
 * 作用：
 */
public class CenterImg {
    public String image;

    public CenterImg(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "CenterImg{" +
                "image='" + image + '\'' +
                '}';
    }
}
