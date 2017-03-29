package yunifang.bwei.com.yunifang.bean;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/28.
 * 作用：
 */
public class XqImg {
    public String normal_url;

    public XqImg(String normal_url) {
        this.normal_url = normal_url;
    }

    public String getNormal_url() {
        return normal_url;
    }

    public void setNormal_url(String normal_url) {
        this.normal_url = normal_url;
    }

    @Override
    public String toString() {
        return "XqImg{" +
                "normal_url='" + normal_url + '\'' +
                '}';
    }
}
